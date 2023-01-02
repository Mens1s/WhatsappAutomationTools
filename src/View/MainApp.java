package View;

import Helper.Config;
import Helper.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MainApp extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_opeartor;
    private JPanel pnl_user_list;
    private JScrollPane scrl_num_list;
    private JTable tbl_num_list;
    private JPanel pnl_user_form;
    private JTextField fld_num;
    private JButton btn_num_delete;
    private JPanel pnl_users_form;
    private JTextField fld_sh_user_name;
    private JButton btn_num_sh;
    private JTable tbl_msg_list;
    private JPanel pnl_patika_add;
    private JButton btn_msg_add;
    private JPanel pnl_top;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JPanel pnl_msg;
    private JButton btn_num_add;
    private JButton btn_excl_num_upload;
    private JEditorPane fld_msg;

    // my objects
    private DefaultTableModel mdl_num_list;
    private Object[] row_num_list;
    private ArrayList<String> numbers;
    public MainApp(){
        add(wrapper);
        setSize(1000, 500);

        setLocation(Helper.screenCenter("x",getSize()), Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        // initializing objects
        numbers = new ArrayList<>();

        // first numbers page start
        Object[] col_num_list = {"Numbers"};
        row_num_list = new Object[col_num_list.length];

        mdl_num_list = new DefaultTableModel();
        mdl_num_list.setColumnIdentifiers(col_num_list);

        load_num_list();

        tbl_num_list.setModel(mdl_num_list);
        // end numbers page
        // add button action listener to add number
        btn_num_add.addActionListener( e -> {
            if (Helper.isEmpty( fld_num ))
                Helper.showMsg("fill");
            else{
                String curr_num = fld_num.getText();
                if( numbers.contains(curr_num) )
                    Helper.showMsg("included");
                else{
                    numbers.add(curr_num);
                    load_num_list();
                }
            }
        });


    }

    private void load_num_list() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_num_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;

        for(String num : numbers){
            row_num_list[i] = num;
            mdl_num_list.addRow(row_num_list);
        }
    }

    public static void main(String[] args) {
        Helper.setLayout();
        MainApp a = new MainApp();
    }
}


