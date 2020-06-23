import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;


public class Letras extends JFrame {
    //final int color = 210;
    final int color = 150;

    static JPanel p = new JPanel(); //panel principal, para organizar las cosas
    static PantallaInput in = new PantallaInput(); //panel en donde se dibuja
    static PantallaPrep prep = new PantallaPrep();// la matriz
    static PantallaOut out = new PantallaOut(); //el output
    static Clear text = new Clear();//boton de borrar
    static GroupLayout layout = new GroupLayout(p);

    public static void main(String[] args) throws Exception{
        new Letras();
    }

    public Letras() throws Exception{
        super("Letras");
        setSize(620,335); //Tamaño de la ventana un pedazo de 200x200 para escribir, una pantalla de 200x200 para mostrar la preparacion, un pedazo de 200x200 el output y una barra para guardar el texto
        setResizable(false); //Haciendo que no se le pueda cambiar el tamaño
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        p.setBackground(new Color(30, 30, 30));

        prep.setBackground(new Color(color, color, color));
        in.setBackground(new Color(color, color, color));
        out.setBackground(new Color(color, color, color));
        text.setBackground(new Color(80, 80, 80));

        p.setMinimumSize(new Dimension(920,520));
        p.setMaximumSize(new Dimension(920,520));
        p.setPreferredSize(new Dimension(920,520));

        in.setPreferredSize(new Dimension(200, 200));
        in.setMinimumSize(new Dimension(200, 200));
        in.setMaximumSize(new Dimension(200, 200));

        prep.setPreferredSize(new Dimension(200, 200));
        prep.setMinimumSize(new Dimension(200, 200));
        prep.setMaximumSize(new Dimension(200, 200));

        out.setPreferredSize(new Dimension(200, 200));
        out.setMinimumSize(new Dimension(200, 200));
        out.setMaximumSize(new Dimension(200, 200));

        text.setMinimumSize(new Dimension(610, 100));
        text.setMaximumSize(new Dimension(610, 100));
        text.setPreferredSize(new Dimension(610, 100));

        p.setLayout(layout);


        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, 5)
                .addGroup(layout.createParallelGroup()
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(in)
                        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, 5)
                        .addComponent(prep)
                        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, 5)
                        .addComponent(out)
                    )
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(text)
                    )
                )
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, 5)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, 5)
                .addGroup(layout.createParallelGroup()
                  .addComponent(in)
                  .addComponent(prep)
                  .addComponent(out)
                )
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, 5)
                .addComponent(text)
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, 5)
        );

        p.add(in);
        p.add(prep);
        p.add(out);
        p.add(text);
        add(p);
        setVisible(true);
    }

}
