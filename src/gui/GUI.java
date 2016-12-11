package gui;

import model.SearchModel;
import search.Search;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by un4 on 11/12/16.
 */
public class GUI implements Observer {

    JFrame frame;
    JPanel mainPanel, centerPanel, sideBar, bottomPanel;
    JButton searchTechnique;
    JRadioButton bfs, aStar, uniform, greedy;
    JButton searchButton;
    SearchModel searchModel;


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
        centerPanel = new JPanel();
        bottomPanel = new JPanel();


        searchButtonGroup = new ButtonGroup();
        comboBox = new JComboBox();

        comboBox.addItem("Manhattan");
        comboBox.addItem("TilesMS");

        // Search options
        bfs = createRadioButton("BFS", searchButtonGroup);
        aStar = createRadioButton("A-Star", searchButtonGroup);
        uniform = createRadioButton("Uniform", searchButtonGroup);
        greedy = createRadioButton("Greedy", searchButtonGroup);

//        Arrays.stream({bfs,aStar,uniform,greedy}).forEach(e -> this.addToPanel(e,sideBar));

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

        centerPanel.add(new JLabel("This is the shit that is lit"));

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    JRadioButton createRadioButton(String name, ButtonGroup group) {
        JRadioButton radioButton = new JRadioButton(name);
        group.add(radioButton);
        return radioButton;
    }


    JButton createButton(String name, JPanel panel) {
        JButton button = new JButton(name);
        panel.add(button);
        return button;
    }


    void addToPanel(JComponent o, JPanel panel) {
        panel.add(o);
    }


    void search() {
        searchModel.search();
    }

    @Override
    public void update(Observable observable, Object o) {
        this.centerPanel.removeAll();
        //To do display
        this.searchModel.getNodes().ifPresent(n -> n.forEach(node -> {
            JButton button = createButton(node.getState().toString() + " this is lit", this.centerPanel);
            this.centerPanel.repaint();
            this.mainPanel.repaint();
        }));
    }
}
