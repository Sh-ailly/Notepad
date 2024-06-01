import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.undo.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
class Notepad 
{
    static JDialog d;
    int pointSize;
    Font textFont;
    JTextArea ta;
    Frame obj;
    Notepad()
    {
    JFrame obj=new JFrame("Notepad");
    obj.setSize(1920,1080);
    obj.setVisible(true);
    obj.setLayout(null);
    obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     ta=new JTextArea();
    obj.add(ta);
    ta.setBounds(0,0,1750,1150);
    Font f1=new Font("Arial",Font.PLAIN,13);
    ta.setFont(f1);

    JScrollPane s=new JScrollPane(ta);
    s.setBounds(0,0,1275,632);
    s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    obj.getContentPane().setLayout(null);
    obj.getContentPane().add(s);

    //to give an image to the icon of notepad
    Image icon=Toolkit.getDefaultToolkit().getImage("D://icon.png");
        obj.setIconImage(icon);

    JMenuBar mb=new JMenuBar();
    obj.setJMenuBar(mb);
    JMenu m1=new JMenu("File");
    mb.add(m1);
    JMenuItem n=new JMenuItem("New");
    m1.add(n);
    JDialog newfile=new JDialog();
    newfile.setTitle("Notepad");
    newfile.setSize(400,200);
    newfile.setLayout(null);
    //for dialogbox at the center
    newfile.setLocationRelativeTo(null);
    n.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
            if(ta.getText().isEmpty())
            {
                ta.setText(null);
            }
            else
            {
                newfile.setVisible(true);
                obj.add(newfile);
            }
        }
    });

    JLabel newfileDialog=new JLabel("Do you want to save change to Untitled");
    newfile.add(newfileDialog);
    newfileDialog.setBounds(65,20,300,40);
    Button savefileDialog=new Button("Save");
    newfile.add(savefileDialog);
    savefileDialog.setBounds(34,85,70,30);
    savefileDialog.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

        }
    });
    Button dontsaveDialog=new Button("Don't save");
    newfile.add(dontsaveDialog);
    dontsaveDialog.setBounds(154,85,70,30);
    dontsaveDialog.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            ta.setText(null);
            newfile.dispose();
        }
    });
    Button cancelDialog=new Button("Cancel");
    newfile.add(cancelDialog);
    cancelDialog.setBounds(274,85,70,30);
    cancelDialog.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            newfile.dispose();

        }
    });  

    JMenuItem nw=new JMenuItem("New window");
    m1.add(nw);
    nw.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            new Notepad();
        }
    });
    JMenuItem open=new JMenuItem("Open");
    m1.add(open);
    JMenuItem save=new JMenuItem("Save");
    m1.add(save);
    JMenuItem saveas=new JMenuItem("Save As");
    m1.add(saveas);
    JMenuItem pagesetup=new JMenuItem("Page Set Up");
    m1.add(pagesetup);
    JMenuItem print=new JMenuItem("Print");
    m1.add(print);
    JMenuItem exit=new JMenuItem("Exit");
    m1.add(exit);
    exit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            if(ta.getText().isEmpty())
            {
                obj.dispose();
            }
            else{
               //here calling the dialog box obj to add the dialog box when exit is being clicked
                JDialog newfileExit=new JDialog();
                newfileExit.setTitle("Notepad");
                newfileExit.setSize(400,200);
                newfileExit.setLayout(null);
                //for dialogbox at the center
                newfileExit.setLocationRelativeTo(null);
                exit.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        if(ta.getText().isEmpty())
                        {
                            obj.dispose();
                        }
                        else
                        {
                            newfileExit.setVisible(true);
                            obj.add(newfileExit);
                        }
                    }
                });
    JLabel newfileDialogExit=new JLabel("Do you want to save change to Untitled");
    newfileExit.add(newfileDialog);
    newfileDialogExit.setBounds(65,20,300,40);
    Button savefileDialogExit=new Button("Save");
    newfileExit.add(savefileDialogExit);
    savefileDialogExit.setBounds(34,85,70,30);
    savefileDialogExit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

        }
    });
    Button dontsaveDialogExit=new Button("Don't save");
    newfileExit.add(dontsaveDialogExit);
    dontsaveDialogExit.setBounds(154,85,70,30);
    dontsaveDialogExit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            ta.setText(null);
            obj.dispose();
            newfileExit.dispose();
        }
    });
    Button cancelDialogExit=new Button("Cancel");
    newfileExit.add(cancelDialogExit);
    cancelDialogExit.setBounds(274,85,70,30);
    cancelDialogExit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            newfileExit.dispose();

        }
    });

            }
        }
    });

    JMenu m2=new JMenu("Edit");
    mb.add(m2);
    UndoManager manager=new UndoManager();
    ta.getDocument().addUndoableEditListener(new UndoableEditListener(){
        public void undoableEditHappened(UndoableEditEvent e){
            manager.addEdit(e.getEdit());
        }
    });
    JMenuItem undo=new JMenuItem("Undo");
    m2.add(undo);
    undo.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            manager.undo();
        }
    });
    JMenuItem redo=new JMenuItem("Redo");
    m2.add(redo);
    redo.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            manager.redo();
        }
    });
    JMenuItem cut=new JMenuItem("Cut");
    m2.add(cut);
    cut.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            ta.cut();
        }
    });
    JMenuItem copy=new JMenuItem("Copy");
    m2.add(copy);
    copy.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            ta.copy();
        }
    });
    JMenuItem paste=new JMenuItem("Paste");
    m2.add(paste);
    paste.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            ta.paste();
        }
    });
    JMenuItem delete=new JMenuItem("Delete");
    m2.add(delete);
    JMenuItem find=new JMenuItem("Find");
    m2.add(find);
    JMenuItem findnext=new JMenuItem("Find Next");
    m2.add(findnext);
    JMenuItem findprevious=new JMenuItem("Find Previous");
    m2.add(findprevious);
    JMenuItem replace=new JMenuItem("Replace");
    m2.add(replace);
    JMenuItem go_to=new JMenuItem("Go to");
    m2.add(go_to);
    JDialog gotoline=new JDialog();
    JLabel lineno=new JLabel("Line number");
    lineno.setBounds(20,20,100,30);
    gotoline.add(lineno);
    JTextField gototf=new JTextField("1234");
    gototf.setBounds(20,50,350,30);
    gotoline.add(gototf);
    Button gotobu=new Button("Go to");
    gotoline.add(gotobu);
    gotobu.setBounds(20,100,170,30);
    Button cancelbu=new Button("Cancel");
    gotoline.add(cancelbu);
    cancelbu.setBounds(200,100,170,30);
    go_to.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            gotoline.setTitle("Go to line");
            gotoline.setSize(400,200);
            gotoline.setLayout(null);
            gotoline.setLocationRelativeTo(null);
            gotoline.setVisible(true);
            obj.add(gotoline);
        }
    });
    JMenuItem selectall=new JMenuItem("Select all");
    m2.add(selectall);
    selectall.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            ta.selectAll();
        }
    });
    JMenuItem time=new JMenuItem("time/date");
    m2.add(time);
    JMenuItem font=new JMenuItem("Font");
    m2.add(font);


    JMenu m3=new JMenu("View");
    mb.add(m3);
    JMenu zoom=new JMenu("Zoom");
    m3.add(zoom);
    JMenuItem zoomin=new JMenuItem("Zoom In");
    zoom.add(zoomin);
    zoomin.setActionCommand("Zoom In");
    zoomin.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            
        }
    });
    JMenuItem zoomout=new JMenuItem("Zoom Out");
    zoom.add(zoomout);
    
    JMenuItem restoredefaultzoom=new JMenuItem("Restore Default Zoom");
    zoom.add(restoredefaultzoom);
    JMenuItem statusbar=new JMenuItem("Status Bar");
    m3.add(statusbar);
    JMenuItem wordwrap=new JMenuItem("Word wrap");
    m3.add(wordwrap);

    
    }
 

    public static void main(String[] args)
    {
        Notepad obj1=new Notepad();
    }
}