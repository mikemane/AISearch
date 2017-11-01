package gui;

import datastructures.Node;
import datastructures.State;
import heuristics.ManhattanDistanceHeuristics;
import heuristics.TilesMisplacedHeuristics;
import model.SearchModel;
import search.Search;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by un4 on 11/12/16.
 */
public class GUI implements Observer {

    JFrame frame;
    JPanel mainPanel, topPanel, centerPanel, centerBLeft, centerBRight, centerTopPanel, centerButtomPanel, sideBar, bottomPanel;
    JButton searchTechnique, previousLayer;
    JRadioButton bfs, aStar, uniform, greedy;
    JButton searchButton, customState;
    SearchModel searchModel;
    JLabel searchLabel;
    JTextArea metricsInformation;
    ButtonGroup searchButtonGroup;
    JComboBox comboBox;
    SearchType searchType;
    Integer boardSize;

    public GUI(SearchModel model) {
        this.searchModel = model;
        this.searchModel.addObserver(this);

        frame = new JFrame();
        mainPanel = new JPanel(new BorderLayout());

        GridLayout gridLayout = new GridLayout(10, 1);
        sideBar = new JPanel(gridLayout);

        centerTopPanel = new JPanel();


        metricsInformation = new JTextArea();
        Font f = new Font("Times Roman", Font.BOLD, 15);
        metricsInformation.setFont(f);
        metricsInformation.setText("Statistics");


        centerBRight = new JPanel();
        centerBLeft = new JPanel(new BorderLayout());

        centerBRight.add(metricsInformation);

        centerBRight.setBackground(Color.LIGHT_GRAY);

        centerButtomPanel = new JPanel(new GridLayout(0, 2));

        customState = createButton("Custom Tile", sideBar);
        customState.addActionListener(e -> enterCustomState());


        bottomPanel = new JPanel();


        centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.add(centerTopPanel);
        centerPanel.add(centerButtomPanel);

        centerButtomPanel.add(centerBLeft);
        centerButtomPanel.add(centerBRight);


        bottomPanel = new JPanel();
        topPanel = new JPanel();


        previousLayer = createButton("Previous Layer", this.topPanel);
        previousLayer.addActionListener(e -> this.undoAction());

        searchLabel = new JLabel(" Click tile to expand successors Search Type:");
        topPanel.add(searchLabel);

        searchButtonGroup = new ButtonGroup();
        comboBox = new JComboBox();

        comboBox.addItem("No Heuristics");
        comboBox.addItem("Manhattan");
        comboBox.addItem("TilesMS");

        comboBox.addActionListener(e -> changeHeuristicFunction(comboBox.getSelectedIndex()));

        // Search options
        bfs = createRadioButton("BFS", searchButtonGroup);
        aStar = createRadioButton("A-Star", searchButtonGroup);
        uniform = createRadioButton("Uniform", searchButtonGroup);
        greedy = createRadioButton("Greedy", searchButtonGroup);

        JRadioButton[] radios = {bfs, aStar, uniform, greedy};
        Arrays.stream(radios).forEach(e -> e.addActionListener(actionEvent -> this.addRadioActions(actionEvent.getSource())));

        addToPanel(bfs, sideBar);
        addToPanel(aStar, sideBar);
        addToPanel(uniform, sideBar);
        addToPanel(greedy, sideBar);

        addToPanel(comboBox, sideBar);

        searchButton = createButton("Search", bottomPanel);
        searchButton.addActionListener(e -> this.search());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        mainPanel.setPreferredSize(new Dimension(1000, 1000));
        mainPanel.add(sideBar, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(topPanel, BorderLayout.NORTH);


        frame.add(mainPanel);
        frame.setVisible(true);


        this.setInitialStates();
    }

    void enterCustomState() {
        JTextField xField = new JTextField(20);
        JTextField yField = new JTextField(20);

        JPanel container = new JPanel(new BorderLayout());

        String message = "Start and goal Values in the format E.g: 1,2,3,4,5,6,7,8,0";
        JPanel myPanel = new JPanel();

        myPanel.add(new JLabel("Start:"));
        myPanel.add(xField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Goal:"));
        myPanel.add(yField);

        container.add(new JLabel(message), BorderLayout.NORTH);
        container.add(myPanel, BorderLayout.CENTER);


        int result = JOptionPane.showConfirmDialog(null, container, "Enter new States", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            searchModel.setStartState(getStartFromString(xField.getText()));
            searchModel.goalState(getStartFromString(yField.getText()));
        }

    }

    void setInitialStates() {
        this.centerBLeft.removeAll();
        this.centerBLeft.revalidate();
        //TODO  change this to the current board size.


        JPanel header = new JPanel(new GridLayout(0, 2));


        header.add(new JLabel("Initial State", SwingConstants.CENTER));
        header.add(new JLabel("Goal State", SwingConstants.CENTER));


        JPanel container = new JPanel(new GridLayout(0, 2));

        JPanel initialState = new JPanel(new GridLayout(State.SIZE, State.SIZE));
        initialState.setBorder(new LineBorder(Color.BLACK));

        JPanel goalState = new JPanel(new GridLayout(State.SIZE, State.SIZE));

        goalState.setBorder(new LineBorder(Color.GREEN));

        container.add(initialState);
        container.add(goalState);

        this.centerBLeft.add(header, BorderLayout.NORTH);
        this.centerBLeft.add(container, BorderLayout.CENTER);

        populatePanel(searchModel.getInitialState(), initialState);
        populatePanel(searchModel.getGoalState(), goalState);

        centerBLeft.repaint();


    }


    void populatePanel(State state, JPanel panel) {
        for (int i : state.getCurrentState()) {
            JLabel label;
            if (i == 0) {
                label = new JLabel();
            } else {
                label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            }
            label.setBorder(new LineBorder(Color.BLACK));
            panel.add(label);
        }
    }


    State getStartFromString(String string) {
        int[] currentState = Arrays.stream(string.split(",")).mapToInt(Integer::parseInt).toArray();
        return new State(currentState);

    }

    JRadioButton createRadioButton(String name, ButtonGroup group) {
        JRadioButton radioButton = new JRadioButton(name);
        group.add(radioButton);
        return radioButton;
    }

    void changeHeuristicFunction(int id) {
        if (id == 1) {
            this.searchModel.setHeuristics(new ManhattanDistanceHeuristics());
        } else if (id == 2) {
            this.searchModel.setHeuristics(new TilesMisplacedHeuristics());
        }
    }


    JButton createButton(String name, JPanel panel) {
        JButton button = createButton();
        button.setText(name);
        panel.add(button);
        return button;
    }


    JButton createButton() {
        JButton button = new JButton();
        button.setLayout(new GridLayout(State.SIZE, State.SIZE));
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        button.setSize(100, 100);
        return button;
    }

    void addRadioActions(Object action) {
        JRadioButton button = (JRadioButton) action;
        if (button == bfs)
            this.searchType = SearchType.BFS;
        else if (button == aStar)
            this.searchType = SearchType.ASTAR;
        else if (button == uniform)
            this.searchType = SearchType.UNIFORM;
        else if (button == greedy)
            this.searchType = SearchType.GREEDY;
        else
            this.searchType = SearchType.ASTAR;

        this.searchLabel.setText("   SearchType: " + this.searchType.toString());
        this.searchModel.setSearch(this.searchType);
    }

    void addToPanel(JComponent o, JPanel panel) {
        panel.add(o);
    }


    void search() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to do this");
        if (confirm == JOptionPane.OK_OPTION)
            searchModel.search();
    }

    @Override
    public void update(Observable observable, Object o) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                centerTopPanel.removeAll();
                centerTopPanel.revalidate();
                centerPanel.revalidate();

                setInitialStates();
                searchModel.getNodes().ifPresent(n -> n.forEach(node -> {

                    JButton button = createButton();
                    centerTopPanel.add(button);
                    if (node.isSolutionPath()) {
                        Border line = new LineBorder(Color.GREEN);
                        button.setBorder(line);
                        button.setSize(100, 100);
                    }
                    button.addActionListener(e -> searchModel.expand(node));
                    updateButtonItems(node, button);
                    metricsInformation.setText(searchModel.getStats());


                }));

                centerTopPanel.repaint();
                centerPanel.repaint();
                mainPanel.repaint();
            }
        });
    }


    void updateButtonItems(Node node, JButton button) {
        String text = "<html>";
        int counter = 0;
        for (int i : node.getState().getCurrentState()) {
            if (counter != 0 && counter % State.SIZE == 0) {
                text += " " + " <br />";
                text += "<hr width=\"100%\">";
                text += " |  " + i + "  | ";
            } else {
                if (counter == 0)
                    text += " | ";
                text += "  " + i + "  | ";
            }
            counter++;
        }
        text += "<br /><br /> g(n) " + node.getgCost() + "<br /> h(n) " + node.gethCost() + "<br/>  f(n): " + node.getfCost();
        text += "</html>";
        button.setText(text);
    }


    public void processNodes(HashSet<datastructures.Node> nodes) {
        String buttonValue;
        if (nodes.size() == 1) {
            buttonValue = "EXPAND";
        }
    }

    public void undoAction() {
        this.searchModel.popStack();
    }

    public void exploreChildren(Node node) {
        searchModel.expand(node);
    }


}
