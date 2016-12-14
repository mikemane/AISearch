package gui;

import datastructures.Node;
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
    JButton searchButton;
    SearchModel searchModel;
    JLabel searchLabel;
    JTextArea metricsInformation;


    ButtonGroup searchButtonGroup;
    JComboBox comboBox;
    SearchType searchType;

    public GUI(SearchModel model) {
        this.searchModel = model;
        this.searchModel.addObserver(this);

        frame = new JFrame();
        mainPanel = new JPanel(new BorderLayout());

        GridLayout gridLayout = new GridLayout(10, 1);
        sideBar = new JPanel(gridLayout);

        centerTopPanel = new JPanel();

        metricsInformation = new JTextArea();
        centerBRight = new JPanel();
        centerBLeft = new JPanel(new GridLayout(3, 3));

        centerBRight.add(metricsInformation);

        centerBLeft.setBackground(Color.GREEN);
        centerBRight.setBackground(Color.RED);

        centerButtomPanel = new JPanel(new GridLayout(0, 2));

        centerTopPanel.setBackground(Color.DARK_GRAY);
        centerButtomPanel.setBackground(Color.DARK_GRAY);

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

        searchLabel = new JLabel("  Search Type:");
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

//        centerPanel.add(new JLabel("This is the shit that is lit"));

        frame.add(mainPanel);
        frame.setVisible(true);
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
        button.setLayout(new GridLayout(3, 3));
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
        searchModel.search();
    }

    @Override
    public void update(Observable observable, Object o) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                centerTopPanel.removeAll();
                //To d
                // o display
                searchModel.getNodes().ifPresent(n -> n.forEach(node -> {

                    JButton button = createButton();
                    centerTopPanel.add(button);
                    button.addActionListener(e -> exploreChildren());
                    updateButtonItems(node, button);

                    metricsInformation.setText(searchModel.getStats());
                    centerTopPanel.revalidate();
                    centerTopPanel.repaint();
                    centerPanel.revalidate();
                    centerPanel.repaint();
                    mainPanel.repaint();
                }));
            }
        });
    }


    void updateButtonItems(Node node, JButton button) {
        String text = "<html>";
        int counter = 0;
        for (int i : node.getState().getCurrentState()) {
            if (counter != 0 && counter % 3 == 0) {
                text += " " + " <br />";
                text += "<hr width=\"100%\">";
                text += " |  " + i + "  | ";
            } else {
                if (counter == 0)
                    text += " | ";
                text += "  " + i + "  | ";
            }
            counter++;
//            JLabel label = new JLabel(new String("i"));
        }
        text += "</html>";
        button.setText(text);
    }


    public void processNodes(HashSet<datastructures.Node> nodes) {
        String buttonValue;
        if (nodes.size() == 1) {
            buttonValue = "EXPAND";
        }
    }

//    public void showState()

    public void undoAction() {
        this.searchModel.popStack();
    }

    public void exploreChildren() {

    }
}
