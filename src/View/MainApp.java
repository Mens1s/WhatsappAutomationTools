package View;

import Helper.Config;
import Helper.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JTextField fld_sh_phone_num;
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
    private JScrollPane scrl_msg_list;

    // objects for num list
    private DefaultTableModel mdl_num_list;
    private Object[] row_num_list;
    private ArrayList<String> numbers;

    // objects for msg list
    private DefaultTableModel mdl_msg_list;
    private Object[] row_msg_list;
    private ArrayList<String> messages;
    private JPopupMenu messagePopUpMenu;

    public MainApp(){
        add(wrapper);
        setSize(1000, 500);

        setLocation(Helper.screenCenter("x",getSize()), Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        // initializing objects
        numbers = new ArrayList<>();
        messages = new ArrayList<>();

        // first numbers page start
        Object[] col_num_list = {"Numbers"};
        row_num_list = new Object[col_num_list.length];

        mdl_num_list = new DefaultTableModel();
        mdl_num_list.setColumnIdentifiers(col_num_list);

        load_num_list();

        tbl_num_list.setModel(mdl_num_list);
        // end numbers page
        // msg page
        Object[] col_msg_list = {"Messages"};
        row_msg_list = new Object[col_msg_list.length];

        mdl_msg_list = new DefaultTableModel();
        mdl_msg_list.setColumnIdentifiers(col_msg_list);

        tbl_msg_list.setModel(mdl_msg_list);
        // end msg page
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

        // num search
        btn_num_sh.addActionListener(e -> load_num_list(fld_sh_phone_num.getText()));

        // num delete
        btn_num_delete.addActionListener(e ->{
            if(Helper.isEmpty(fld_num))
                Helper.showMsg("fill");
            else{
                String num = fld_num.getText();
                if( numbers.contains(num) ){
                    numbers.remove(num);
                    load_num_list();
                }else{
                    Helper.showMsg("nInList");
                }
            }
        });
        // message pop up menu
        messagePopUpMenu = new JPopupMenu();
        JMenuItem sendMenu = new JMenuItem("Send Message");
        JMenuItem delMenu = new JMenuItem("Delete Message");
        messagePopUpMenu.add(sendMenu);
        messagePopUpMenu.add(delMenu);

        sendMenu.addActionListener(e ->{

        });
        delMenu.addActionListener(e ->{
            if(Helper.confirm("sure")){
                String msg = (tbl_msg_list.getValueAt(tbl_msg_list.getSelectedRow(), 0 ).toString());
                messages.remove(msg);

                load_msg_list();
                Helper.showMsg("done");
            }
        });

        tbl_msg_list.setComponentPopupMenu(messagePopUpMenu);
        // when press right click this function click left too
        tbl_msg_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_msg_list.rowAtPoint(point);

                tbl_msg_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });
        // message add to shortcut
        btn_msg_add.addActionListener(e -> {
            String msg = fld_msg.getText();
            if( msg.isEmpty() )
                Helper.showMsg("fill");
            else{
                messages.add(msg);
                load_msg_list();
            }
        }
        );
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
    private void load_num_list(String target) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_num_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;

        for(String num : numbers){
            if ( num.startsWith(target) ){
                row_num_list[i] = num;
                mdl_num_list.addRow(row_num_list);
            }

        }
    }

    private void load_msg_list() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_msg_list.getModel();
        clearModel.setRowCount(0);

        for(String msg : messages) {
            row_msg_list[0] = msg;
            mdl_msg_list.addRow(row_msg_list);
        }
    }

    public static void main(String[] args) {
        Helper.setLayout();
        MainApp a = new MainApp();
    }
}


