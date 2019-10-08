/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicai;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author RAFAEL RAMIREZ
 */
public class Ventana {
    
    JButton agregar;
    JButton cancelar;
    CardLayout cards;
    JPanel principal, menu;
    JComboBox logins;
    JTextField Log;
    JRadioButton j1, j2;
    JComboBox carreralist;
    JComboBox userlist;
    JButton seleccionar;
    JButton volver,volver1,volver2,volver3,volver4;
    JPanel cargardatos, jugar, juegocargado, instruc, cred, niv;
    JLabel en1, en2, cb1,cb2,cb3,cb4,cb5;
    
    JButton juegonuevo, cargar, salir, instrucciones, creditos, niveles;
    
    StringTokenizer tok;
    
    String usuarios[] = new String[100];
    
    String carreras[]  = {
            "Ing. Informatica","Ing. Electronica","Ing. Industrial","Ing. Mecánica","Ing. Ambiental","Arquitectura",
            "Licenciatura en Música","Ing. en Producción Animal","Ing. Civil"
        };
    int orden[];
    int segundos;
    int posorden = 0;
    FileReader fr;
    BufferedReader br;
    
    URL url1;
    URL url2;
    URL urlc1;
    URL urlc2;
    URL urlc3;
    URL urlc4;
    URL urlc5;
    
    ImageIcon enano;
    ImageIcon enana;
    ImageIcon cu1,cu2,cu3,cu4,cu5;
    Boolean pri = false, seg = false , ter = false, cuart = false , quin = false;
    int score;
    
    Action action1;
    
    Timer tiempo;
    
    public Ventana(){
        JFrame ventana = new JFrame("Registrar Usuario");
        
        ButtonGroup botones = new ButtonGroup();
        //Paneles
        principal = new JPanel();
        cards = new CardLayout();
        principal.setLayout(cards);
        
        JPanel log = new JPanel();
        log.setLayout(null);
        
        seleccionar = new JButton("Seleccionar");
        seleccionar.setBounds(150, 200, 200, 20);
        
        juegonuevo = new JButton("Juego Nuevo");
        juegonuevo.setBounds(30, 20, 150, 20);
        
        cargar = new JButton("Cargar");
        cargar.setBounds(30, 50, 150, 20);
        
        salir = new JButton("Salir");
        salir.setBounds(30, 80, 150, 20);
        
        instrucciones = new JButton("Instrucciones");
        instrucciones.setBounds(190, 20, 150, 20);
        
        
        creditos = new JButton("Creditos");
        creditos.setBounds(190, 50, 150, 20);
        
        niveles = new JButton("Nivel");
        niveles.setBounds(190, 80, 150, 20);
        //paneles
        cargardatos = new JPanel();
        cargardatos.setLayout(null);
        
        volver = new JButton("Volver");
        volver.setBounds(150, 220, 200, 20);
        volver1 = new JButton("Volver");
        volver1.setBounds(150, 200, 200, 20);
        volver2 = new JButton("Volver");
        volver2.setBounds(150, 200, 200, 20);
        volver3 = new JButton("Volver");
        volver3.setBounds(150, 200, 200, 20);
        volver4 = new JButton("Volver");
        volver4.setBounds(150, 200, 200, 20);
        
        jugar = new JPanel();
        jugar.setLayout(null);
        
        //En este espacio, va todo lo que debe ser añadido  a la pantalla de juego
        
        url1 = this.getClass().getResource("enano.png");
        url2 = this.getClass().getResource("enana.png");
        urlc1 = this.getClass().getResource("cubo1.png");
        urlc2 = this.getClass().getResource("cubo2.png");
        urlc3 = this.getClass().getResource("cubo3.png");
        urlc4 = this.getClass().getResource("cubo4.png");
        urlc5 = this.getClass().getResource("cubo5.png");
        
        enano = new ImageIcon(url1);
        enana = new ImageIcon(url2);
        cu1 = new ImageIcon(urlc1);
        cu2 = new ImageIcon(urlc2);
        cu3 = new ImageIcon(urlc3);
        cu4 = new ImageIcon(urlc4);
        cu5 = new ImageIcon(urlc5);
        
        en1 = new JLabel(enano);
        en2 = new JLabel(enana);
        en1.setBounds(200, 150, enano.getIconWidth(), enano.getIconHeight());
        cb1 = new JLabel(cu1);
        cb2 = new JLabel(cu2);
        cb3 = new JLabel(cu3);
        cb4 = new JLabel(cu4);
        cb5 = new JLabel(cu5);
        
        cb1.setBounds(0, 0, cu1.getIconWidth(), cu1.getIconHeight());
        cb2.setBounds(cu1.getIconWidth(), 0,cu1.getIconWidth() , cu1.getIconHeight());
        cb3.setBounds(cu1.getIconWidth()*2, 0, cu1.getIconWidth(), cu1.getIconHeight());
        cb4.setBounds(cu1.getIconWidth()*3, 0, cu1.getIconWidth(), cu1.getIconHeight());
        cb5.setBounds(cu1.getIconWidth()*4, 0, cu1.getIconWidth(), cu1.getIconHeight());
        
        jugar.add(en1);
        jugar.add(cb1);
        jugar.add(cb2);
        jugar.add(cb3);
        jugar.add(cb4);
        jugar.add(cb5);
        
        ventana.addKeyListener(new KeyAdapter(){
                public void keyPressed(KeyEvent e){
                        if(e.getKeyCode()==e.VK_RIGHT){
                            en1.setBounds(en1.getX()+5, en1.getY(), en1.getWidth(), en1.getHeight());
                            System.out.println("Fue presionado hacia la derecha");
                        }
                        if(e.getKeyCode()==e.VK_LEFT){
                            en1.setBounds(en1.getX()-5, en1.getY(), en1.getWidth(), en1.getHeight());
                            System.out.println("Fue presionado hacia la izquierda");
                        }
                    }
                });
        
        //En este espacio, va todo lo que debe ser añadido  a la pantalla de juego
        
        jugar.add(volver);
        
        juegocargado = new JPanel();
        juegocargado.setLayout(null);
        juegocargado.add(volver1);
        
        instruc = new JPanel();
        instruc.setLayout(null);
        instruc.add(volver2);
        
        JLabel defins = new JLabel("Utiliza las teclas direccionales para ");
        JLabel defins2= new JLabel("moverte de lado a lado");
        JLabel defins3= new JLabel("Atrapa los rectangulos que van cayendo");
        JLabel defins4= new JLabel("Cada uno tiene un valor diferente");
        JLabel defins5= new JLabel("El rojo vale 10 puntos, el Azul vale 20, el Amarillo 30 y el Verde 40");
        JLabel defins6= new JLabel("Si no atrapas alguno, se te restaran 20 puntos");
        JLabel defins7= new JLabel("Buena suerte!");
        defins.setBounds(20, 0, 300, 20);
        defins2.setBounds(20, 20, 300, 20);
        defins3.setBounds(20, 40, 300, 20);
        defins4.setBounds(20, 60, 300, 20);
        defins5.setBounds(20, 80, 400, 20);
        defins6.setBounds(20, 100, 300, 20);
        defins7.setBounds(20, 120, 300, 20);
        instruc.add(defins);
        instruc.add(defins2);
        instruc.add(defins3);
        instruc.add(defins4);
        instruc.add(defins5);
        instruc.add(defins6);
        instruc.add(defins7);
        
        
        cred = new JPanel();
        cred.setLayout(null);
        cred.add(volver3);
        
        niv = new JPanel();
        niv.setLayout(null);
        niv.add(volver4);
        
        menu = new JPanel();
        menu.setLayout(null);
        menu.add(juegonuevo);
        menu.add(cargar);
        menu.add(salir);
        menu.add(instrucciones);
        menu.add(creditos);
        menu.add(niveles);
        //Layout
        
        
        
        //Declaraciones
        JLabel login = new JLabel("Login");
        login.setBounds(20, 20, 200, 20);
        
        carreralist = new JComboBox(carreras);
        carreralist.setMaximumRowCount(5);
        carreralist.setBounds(80, 110, 200, 20);
        
        JLabel sexo = new JLabel("Sexo");
        sexo.setBounds(20, 50, 200, 20);
        
        JLabel masculino = new JLabel("Masculino");
        masculino.setBounds(80, 50, 200, 20);
        JLabel femenino = new JLabel("Femenino");
        femenino.setBounds(170, 50, 200, 20);
        
        JLabel carrera = new JLabel("Carrera");
        carrera.setBounds(20, 110, 200, 20);
        
        j1 = new JRadioButton();
        j1.setBounds(60, 50, 20, 20);
        j2 = new JRadioButton();
        j2.setBounds(150, 50, 20, 20);
        j1.setSelected(true);
        
        botones.add(j2);
        botones.add(j1);
        
        Log = new JTextField();
        Log.setBounds(80, 20, 200, 20);
        
        //Buttons
        agregar = new JButton("Guardar en archivo");
        agregar.setBounds(280, 200, 200, 20);
        cancelar = new JButton("Cancelar");
        cancelar.setBounds(280, 230, 200, 20);
        
        
        //adds
        log.add(login);
        log.add(sexo);
        log.add(carrera);
        log.add(masculino);
        log.add(femenino);
        log.add(j1);
        log.add(j2);
        log.add(carreralist);
        log.add(agregar);
        log.add(cancelar);
        log.add(Log);
        //Manejador de botones
        ButtonHandler x = new ButtonHandler();
        agregar.addMouseListener(x);
        cancelar.addMouseListener(x);
        seleccionar.addMouseListener(x);
        cargardatos.add(seleccionar);
        juegonuevo.addMouseListener(x);
        cargar.addMouseListener(x);
        salir.addMouseListener(x);
        instrucciones.addMouseListener(x);
        creditos.addMouseListener(x);
        niveles.addMouseListener(x);
        volver.addMouseListener(x);
        volver1.addMouseListener(x);
        volver2.addMouseListener(x);
        volver3.addMouseListener(x);
        volver4.addMouseListener(x);
        //principal adds
        principal.add(log,"1");
        principal.add(cargardatos,"2");
        principal.add(menu,"3");
        principal.add(jugar,"4");
        principal.add(juegocargado,"5");
        principal.add(instruc,"6");
        principal.add(cred,"7");
        principal.add(niv,"8");
        cards.show(principal,"4");
        
         tiempo = new Timer(1000, new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        //Aqui añadimos la lógica detras de todo
                        //Pues este timer corre cada segundo
                        if(orden == null){
                            //Generamos los numeros
                            orden = new int[5];
                            Random r = new Random();
        
                            int flag = 0;
                            for(int x = 0;x<5;x++){
                                int ayudante = r.nextInt((5-1)+1) + 1;
                                        if(x==0){
                                            orden[x] = ayudante;
                                        }else if(x>0){
                                            for(int y = 0;y<x;y++){
                                                if(ayudante==orden[y]){
                                                    flag = 1;
                                                    break;
                                                }else{
                                                    flag = 0;
                                                }
                                            }
                                            if(flag==1){
                                                x--;
                                            }else{
                                                orden[x] = ayudante;
                                            }
                                        }
                                    }
                        }
                        //Ahora, con el orden guardado, procedemos a hacer la revision cada 4 segundos
                        if(segundos%4==0 && segundos <20){
                            switch(orden[posorden]){
                                case 1:
                                    pri = true;
                                    break;
                                case 2:
                                    seg = true;
                                    break;
                                case 3:
                                    ter = true;
                                    break;
                                case 4:
                                    cuart = true;
                                    break;
                                case 5:
                                    quin = true;
                                    break;
                            }
                            //Ya en este punto, hemos verificado cual esta listo
                            //Procedemos a mover los verificados
                            posorden++;
                        }
                        
                        if(pri == true){
                            cb1.setLocation(cb1.getX(), cb1.getY()+20);
                        }
                        
                        if(seg == true){
                            cb2.setLocation(cb2.getX(), cb2.getY()+20);
                        }
                        
                        if(ter == true){
                            cb3.setLocation(cb3.getX(), cb3.getY()+20);
                        }
                        
                        if(cuart == true){
                            cb4.setLocation(cb4.getX(), cb4.getY()+20);
                        }
                        
                        if(quin == true){
                            cb5.setLocation(cb5.getX(), cb5.getY()+20);
                        }
                        
                        //Todo lo anterior funciona
                        //En este punto, hacemos verificaciones y luego limpiamos todo al terminar
                        
                        if(cb1.getY()>= en1.getY() && cb1.getX()<= en1.getX() && cb1.getX()+cb1.getWidth()>=en1.getX() && pri == true){
                            score+=10;
                            System.out.print(score);
                            cb1.setVisible(false);
                            pri = false;
                        }else if(cb1.getY()>= en1.getY() && cb1.getX()>en1.getX()+en1.getWidth() && pri == true || cb1.getY()>= en1.getY() && cb1.getX()+cb1.getWidth()<en1.getX() && pri == true){
                            cb1.setVisible(false);
                            score-=10;
                            pri = false;
                            System.out.println(score);
                        }
                        
                        if(cb2.getY()>= en1.getY() && cb2.getX()<= en1.getX() && cb2.getX()+cb2.getWidth()>=en1.getX() && seg == true){
                            score+=10;
                            System.out.print(score);
                            cb2.setVisible(false);
                            seg = false;
                        }else if(cb2.getY()>= en1.getY() && cb2.getX()>en1.getX()+en1.getWidth() && seg == true || cb2.getY()>= en1.getY() && cb2.getX()+cb2.getWidth()<en1.getX() && seg == true){
                            cb2.setVisible(false);
                            score-=10;
                            seg = false;
                            System.out.println(score);
                        }
                        
                        if(cb3.getY()>= en1.getY() && cb3.getX()<= en1.getX() && cb3.getX()+cb3.getWidth()>=en1.getX() && ter == true){
                            score+=10;
                            System.out.print(score);
                            cb3.setVisible(false);
                            ter = false;
                        }else if(cb3.getY()>= en1.getY() && cb3.getX()>en1.getX()+en1.getWidth() && ter == true || cb3.getY()>= en1.getY() && cb3.getX()+cb3.getWidth()<en1.getX() && ter == true){
                            cb3.setVisible(false);
                            score-=10;
                            ter = false;
                            System.out.println(score);
                        }
                        
                        if(cb4.getY()>= en1.getY() && cb4.getX()<= en1.getX() && cb4.getX()+cb4.getWidth()>=en1.getX() && cuart == true){
                            score+=10;
                            System.out.print(score);
                            cb4.setVisible(false);
                            cuart = false;
                        }else if(cb4.getY()>= en1.getY() && cb4.getX()>en1.getX()+en1.getWidth() && cuart == true || cb4.getY()>= en1.getY() && cb4.getX()+cb4.getWidth()<en1.getX() && cuart == true){
                            cb4.setVisible(false);
                            score-=10;
                            cuart = false;
                            System.out.println(score);
                        }
                        
                        if(cb5.getY()>= en1.getY() && cb5.getX()<= en1.getX() && cb5.getX()+cb5.getWidth()>=en1.getX() && quin == true){
                            score+=10;
                            System.out.print(score);
                            cb5.setVisible(false);
                            quin = false;
                        }else if(cb5.getY()>= en1.getY() && cb5.getX()>en1.getX()+en1.getWidth() && quin == true || cb5.getY()>= en1.getY() && cb5.getX()+cb5.getWidth()<en1.getX() && quin == true){
                            cb5.setVisible(false);
                            score-=10;
                            quin = false;
                            System.out.println(score);
                        }
                        
                        //Todos funcionan como deben, ahora, a reiniciar todo
                        
                        segundos++;
                        
                        if(pri==false && seg==false && ter == false && cuart == false && quin == false){
                            cb1.setVisible(true);
                            cb2.setVisible(true);
                            cb3.setVisible(true);
                            cb4.setVisible(true);
                            cb5.setVisible(true);
                            pri = false;
                            seg = false;
                            ter = false;
                            cuart = false;
                            quin = false;
                            cb1.setLocation(cb1.getX(), 0);
                            cb2.setLocation(cb2.getX(), 0);
                            cb3.setLocation(cb3.getX(), 0);
                            cb4.setLocation(cb4.getX(), 0);
                            cb5.setLocation(cb5.getX(), 0);
                            segundos = 0;
                            posorden = 0;
                            orden = null;
                            tiempo.stop();
                            cards.show(principal, "3");
                            JOptionPane.showMessageDialog(null, "Puntaje :" + score);
                            score = 0;
                        }
                    }
                    
        }); 
        
        ventana.add(principal);
        ventana.setFocusable(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500,300);
        ventana.setVisible(true);
    }
    
    class ButtonHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e)  {
            if(e.getSource()==agregar){
                //Here, we save everything to a text file
                FileWriter fw = null;
                try {
                    fw = new FileWriter("usuarios.txt",true);
                } catch (IOException ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter salida_archivo = new PrintWriter(bw,true);
                
                    //Debe seleccionar un nombre
                String logtext = null;
                try{
                    logtext = Log.getText();
                }catch(NullPointerException f){
                    //Message here
                    logtext = null;
                };
                
                String sex = null;
                if(j1.isSelected()){
                    sex = "Masculino";
                }else if(j2.isSelected()){
                    sex = "Femenino";
                }
                
                int indice = carreralist.getSelectedIndex();
                String carrera = carreras[indice];
                
                String user = logtext+"-"+sex+"-"+carrera;
                
                
                if("".equals(Log.getText())){
                    //message
                    cards.show(principal,"1");
                }else{
                    salida_archivo.print(user);
                    salida_archivo.println();
                    salida_archivo.close();
                    
                    String linea;
                    String nom;
                    String se;
                    String car;
                    String complete;
                    int numero = 0;
                    
                    try {
                        fr = new FileReader("usuarios.txt");
                        br = new BufferedReader(fr);
                        linea = br.readLine();
                        while(linea!=null){
                            tok = new StringTokenizer(linea);
                            nom = tok.nextToken("-");
                            se = tok.nextToken("-");
                            car = tok.nextToken("-");
                            complete = nom+", "+se+", "+car;
                            usuarios [numero] = complete;
                            numero++;
                            linea = br.readLine();
                        }
                        numero = 0;
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    //Aqui va un filereader
                    userlist = new JComboBox(usuarios);
                    userlist.setBounds(150, 150, 200, 20);
                    cargardatos.add(userlist);
                    
                    cards.show(principal, "2");
                }
                
                
            }else if(e.getSource()==cancelar){
                Log.setText("");
                j1.setSelected(true);
                j2.setSelected(false);
                carreralist.setSelectedIndex(0);
            }else if(e.getSource()==seleccionar){
                //Aqui tomamos los datos de usuario, y verificamos el sexo para cambiar el personaje
                cards.show(principal, "3");
            }else if(e.getSource()==juegonuevo){
                //Aqui va el timer para el juego nuevo
                cb1.setVisible(true);
                cb2.setVisible(true);
                cb3.setVisible(true);
                cb4.setVisible(true);
                cb5.setVisible(true);
                cb1.setLocation(cb1.getX(), 0);
                cb2.setLocation(cb2.getX(), 0);
                cb3.setLocation(cb3.getX(), 0);
                cb4.setLocation(cb4.getX(), 0);
                cb5.setLocation(cb5.getX(), 0);
                pri = false;
                            seg = false;
                            ter = false;
                            cuart = false;
                            quin = false;
                segundos = 0;
                posorden = 0;
                orden = null;
                tiempo.stop();
                cards.show(principal, "3");
                score = 0;
                tiempo.start();
                
                
                
                cards.show(principal, "4");
            }else if(e.getSource()==cargar){
                cards.show(principal, "5");
            }else if(e.getSource()==salir){
                
            }else if(e.getSource()==instrucciones){
                cards.show(principal, "6");
            }else if(e.getSource()==creditos){
                cards.show(principal, "7");
            }else if(e.getSource()==niveles){
                cards.show(principal, "8");
            }else if(e.getSource()==volver || e.getSource()==volver1 || e.getSource()==volver2 || e.getSource()==volver3 || e.getSource()==volver4){
                if(e.getSource()==volver){
                    tiempo.stop();
                    cb1.setLocation(0, 0);
                    cb2.setLocation(cb1.getWidth(), 0);
                    cb3.setLocation(cb1.getWidth()*2, 0);
                    cb4.setLocation(cb1.getWidth()*3, 0);
                    cb5.setLocation(cb1.getWidth()*4, 0);
                    cb1.setVisible(true);
                    cb2.setVisible(true);
                    cb3.setVisible(true);
                    cb4.setVisible(true);
                    cb5.setVisible(true);
                    pri = false;
                            seg = false;
                            ter = false;
                            cuart = false;
                            quin = false;
                    cb1.setLocation(cb1.getX(), 0);
                    cb2.setLocation(cb2.getX(), 0);
                    cb3.setLocation(cb3.getX(), 0);
                    cb4.setLocation(cb4.getX(), 0);
                    cb5.setLocation(cb5.getX(), 0);
                    segundos = 0;
                    posorden = 0;
                    orden = null;
                    score = 0;
                    tiempo.stop();
                }
                cards.show(principal,"3");
            }
        }
        
       

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
        
    }
}
