import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class GameGUI {

    private JFrame frame;
    private GameMapPanel gameMapPanel; // Neues Panel für das Spielbrett

    public GameGUI() {
        frame = new JFrame("Scotland Yard Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton startButton = new JButton("Start Game");

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(startButton, BorderLayout.NORTH);

        // Hier füge das Bild (JLabel) ein
        ImageIcon mapImage = new ImageIcon("template/scot_yard.png");
        gameMapPanel = new GameMapPanel(mapImage); // Neues Panel für das Spielbrett
        frame.getContentPane().add(gameMapPanel, BorderLayout.CENTER);

        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public void drawPlayers(List<Detective> detectives,MisterX misterX) {
        // Hier rufe eine Methode auf dem GameMapPanel auf, um die Spieler zu zeichnen
        gameMapPanel.drawPlayers(detectives,misterX);
    }

    // Neues Panel für das Spielbrett
    private class GameMapPanel extends JPanel {
        private ImageIcon mapImage;
        private List<Detective> detectives;
        private MisterX misterX;

        public GameMapPanel(ImageIcon mapImage) {
            this.mapImage = mapImage;
        }

        public void drawPlayers(List<Detective> detectives,MisterX misterX) {
            this.detectives = detectives;
            this.misterX=misterX;
            repaint(); // Löst die Neuzeichnung des Panels aus
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Zeichne das Bild
            if (mapImage != null) {
                g.drawImage(mapImage.getImage(), getWidth()/4, getHeight()/4, 760, 570, this);
            }

            // Zeichne die Spieler als Kreise
            if (detectives != null) {
                Color[] colors=new Color[5];
                colors[0]=Color.RED;
                colors[1]=Color.BLUE;
                colors[2]=Color.GREEN;
                colors[3]=Color.YELLOW;
                colors[4]=Color.BLACK;
                for (Detective detective : detectives) {
                    int index=detectives.indexOf(detective);
                    int[] coords=FieldIdToCoords(detective.getCurrentField().getId());
                    int x = coords[0]; // Du musst die tatsächlichen Koordinaten deiner Spieler verwenden
                    int y = coords[1];
                    Color transparentColor = new Color(colors[index].getRed(), colors[index].getGreen(), colors[index].getBlue(), 200); // 128 steht für die Transparenz (0-255)
                    g.setColor(transparentColor);

                    g.fillOval(x, y, 40, 40); 

                    String fieldIdText = "Detective" +detective.getId()+" steht auf dem Feld "+ detective.getCurrentField().getId();
                    g.setColor(Color.BLACK);
                    g.drawString(fieldIdText, 1300,getHeight()/2+20*index); // Just ein Beispiel, passe die Positionierung nach Bedarf an
                }
                int[] coords=FieldIdToCoords(misterX.getCurrentField().getId());
                int x = coords[0];
                int y = coords[1];
                Color transparentColor = new Color(colors[4].getRed(), colors[4].getGreen(), colors[4].getBlue(), 200); // 128 steht für die Transparenz (0-255)
                g.setColor(transparentColor);
                g.fillOval(x, y, 40, 40); 
               

            }
        }

        private int[] FieldIdToCoords(int fieldId){
            int[] a=new int[2];
            int anchorPointX=getWidth()/4-20;
            int anchorPointY=getHeight()/4-20;
            a[0]=anchorPointX;
            a[1]=anchorPointY;

            switch (fieldId){
                case 1:
                    a[0]+=107;
                    a[1]+=31;
                    break;
                case 2:
                    a[0]+=247;
                    a[1]+=36;
                    break;
                case 3:
                    a[0]+=302;
                    a[1]+=27;
                    break;
                case 4:
                    a[0]+=372;
                    a[1]+=32;
                    break;
                case 5:
                    a[0]+=585;
                    a[1]+=34;
                    break;
                case 6:
                    a[0]+=629;
                    a[1]+=35;
                    break;
                case 7:
                    a[0]+=700;
                    a[1]+=36;
                    break;
                case 8:
                    a[0]+=71;
                    a[1]+=58;
                    break;
                case 9:
                    a[0]+=127;
                    a[1]+=59;
                    break;
                case 10:
                    a[0]+=260;
                    a[1]+=70;
                    break;
                case 11:
                    a[0]+=300;
                    a[1]+=60;
                    break;
                case 12:
                    a[0]+=338;
                    a[1]+=66;
                    break;
                case 13:
                    a[0]+=402;
                    a[1]+=67;
                    break;
                case 14:
                    a[0]+=463;
                    a[1]+=47;
                    break;
                case 15:
                    a[0]+=524;
                    a[1]+=34;
                    break;
                case 16:
                    a[0]+=592;
                    a[1]+=68;
                    break;
                case 17:
                    a[0]+=697;
                    a[1]+=84;
                    break;
                case 18:
                    a[0]+=30;
                    a[1]+=90;
                    break;
                case 19:
                    a[0]+=100;
                    a[1]+=87;
                    break;
                case 20:
                    a[0]+=158;
                    a[1]+=83;
                    break;
                case 21:
                    a[0]+=217;
                    a[1]+=109;
                    break;
                case 22:
                    a[0]+=305;
                    a[1]+=104;
                    break;
                case 23:
                    a[0]+=351;
                    a[1]+=92;
                    break;
                case 24:
                    a[0]+=424;
                    a[1]+=94;
                    break;
                case 25:
                    a[0]+=474;
                    a[1]+=81;
                    break;
                case 26:
                    a[0]+=514;
                    a[1]+=69;
                    break;
                case 27:
                    a[0]+=525;
                    a[1]+=93;
                    break;
                case 28:
                    a[0]+=559;
                    a[1]+=83;
                    break;
                case 29:
                    a[0]+=639;
                    a[1]+=103;
                    break;
                case 30:
                    a[0]+=723;
                    a[1]+=113;
                    break;
                case 31:
                    a[0]+=63;
                    a[1]+=122;
                    break;
                case 32:
                    a[0]+=130;
                    a[1]+=127;
                    break;
                case 33:
                    a[0]+=182;
                    a[1]+=120;
                    break;
                case 34:
                    a[0]+=276;
                    a[1]+=120;
                    break;
                case 35:
                    a[0]+=317;
                    a[1]+=138;
                    break;
                case 36:
                    a[0]+=349;
                    a[1]+=146;
                    break;
                case 37:
                    a[0]+=380;
                    a[1]+=124;
                    break;
                case 38:
                    a[0]+=441;
                    a[1]+=121;
                    break;
                case 39:
                    a[0]+=488;
                    a[1]+=107;
                    break;
                case 40:
                    a[0]+=537;
                    a[1]+=129;
                    break;
                case 41:
                    a[0]+=579;
                    a[1]+=117;
                    break;
                case 42:
                    a[0]+=686;
                    a[1]+=131;
                    break;
                case 43:
                    a[0]+=32;
                    a[1]+=137;
                    break;
                case 44:
                    a[0]+=90;
                    a[1]+=143;
                    break;
                case 45:
                    a[0]+=158;
                    a[1]+=153;
                    break;
                case 46:
                    a[0]+=207;
                    a[1]+=148;
                    break;
                case 47:
                    a[0]+=243;
                    a[1]+=133;
                    break;
                case 48:
                    a[0]+=286;
                    a[1]+=158;
                    break;
                case 49:
                    a[0]+=363;
                    a[1]+=167;
                    break;
                case 50:
                    a[0]+=394;
                    a[1]+=149;
                    break;
                case 51:
                    a[0]+=460;
                    a[1]+=148;
                    break;
                case 52:
                    a[0]+=498;
                    a[1]+=136;
                    break;
                case 53:
                    a[0]+=547;
                    a[1]+=166;
                    break;
                case 54:
                    a[0]+=578;
                    a[1]+=150;
                    break;
                case 55:
                    a[0]+=628;
                    a[1]+=144;
                    break;
                case 56:
                    a[0]+=713;
                    a[1]+=172;
                    break;
                case 57:
                    a[0]+=47;
                    a[1]+=167;
                    break;
                case 58:
                    a[0]+=111;
                    a[1]+=167;
                    break;
                case 59:
                    a[0]+=148;
                    a[1]+=199;
                    break;
                case 60:
                    a[0]+=183;
                    a[1]+=183;
                    break;
                case 61:
                    a[0]+=220;
                    a[1]+=192;
                    break;
                case 62:
                    a[0]+=253;
                    a[1]+=173;
                    break;
                case 63:
                    a[0]+=293;
                    a[1]+=209;
                    break;
                case 64:
                    a[0]+=329;
                    a[1]+=210;
                    break;
                case 65:
                    a[0]+=354;
                    a[1]+=194;
                    break;
                case 66:
                    a[0]+=388;
                    a[1]+=196;
                    break;
                case 67:
                    a[0]+=418;
                    a[1]+=178;
                    break;
                case 68:
                    a[0]+=477;
                    a[1]+=176;
                    break;
                case 69:
                    a[0]+=520;
                    a[1]+=183;
                    break;
                case 70:
                    a[0]+=577;
                    a[1]+=180;
                    break;
                case 71:
                    a[0]+=621;
                    a[1]+=173;
                    break;
                case 72:
                    a[0]+=674;
                    a[1]+=168;
                    break;
                case 73:
                    a[0]+=52;
                    a[1]+=196;
                    break;
                case 74:
                    a[0]+=80;
                    a[1]+=217;
                    break;
                case 75:
                    a[0]+=119;
                    a[1]+=220;
                    break;
                case 76:
                    a[0]+=173;
                    a[1]+=218;
                    break;
                case 77:
                    a[0]+=192;
                    a[1]+=238;
                    break;
                case 78:
                    a[0]+=226;
                    a[1]+=227;
                    break;
                case 79:
                    a[0]+=260;
                    a[1]+=214;
                    break;
                case 80:
                    a[0]+=328;
                    a[1]+=248;
                    break;
                case 81:
                    a[0]+=359;
                    a[1]+=247;
                    break;
                case 82:
                    a[0]+=386;
                    a[1]+=230;
                    break;
                case 83:
                    a[0]+=418;
                    a[1]+=238;
                    break;
                case 84:
                    a[0]+=460;
                    a[1]+=201;
                    break;
                case 85:
                    a[0]+=494;
                    a[1]+=206;
                    break;
                case 86:
                    a[0]+=530;
                    a[1]+=217;
                    break;
                case 87:
                    a[0]+=573;
                    a[1]+=234;
                    break;
                case 88:
                    a[0]+=601;
                    a[1]+=253;
                    break;
                case 89:
                    a[0]+=610;
                    a[1]+=214;
                    break;
                case 90:
                    a[0]+=660;
                    a[1]+=200;
                    break;
                case 91:
                    a[0]+=703;
                    a[1]+=208;
                    break;
                case 92:
                    a[0]+=41;
                    a[1]+=231;
                    break;
                case 93:
                    a[0]+=43;
                    a[1]+=267;
                    break;
                case 94:
                    a[0]+=82;
                    a[1]+=263;
                    break;
                case 95:
                    a[0]+=135;
                    a[1]+=253;
                    break;
                case 96:
                    a[0]+=213;
                    a[1]+=273;
                    break;
                case 97:
                    a[0]+=243;
                    a[1]+=264;
                    break;
                case 98:
                    a[0]+=274;
                    a[1]+=253;
                    break;
                case 99:
                    a[0]+=303;
                    a[1]+=262;
                    break;
                case 100:
                    a[0]+=350;
                    a[1]+=280;
                    break;
                case 101:
                    a[0]+=387;
                    a[1]+=263;
                    break;
                case 102:
                    a[0]+=455;
                    a[1]+=239;
                    break;
                case 103:
                    a[0]+=500;
                    a[1]+=227;
                    break;
                case 104:
                    a[0]+=544;
                    a[1]+=257;
                    break;
                case 105:
                    a[0]+=646;
                    a[1]+=244;
                    break;
                case 106:
                    a[0]+=687;
                    a[1]+=240;
                    break;
                case 107:
                    a[0]+=725;
                    a[1]+=237;
                    break;
                case 108:
                    a[0]+=644;
                    a[1]+=308;
                    break;
                case 109:
                    a[0]+=250;
                    a[1]+=304;
                    break;
                case 110:
                    a[0]+=280;
                    a[1]+=278;
                    break;
                case 111:
                    a[0]+=289;
                    a[1]+=306;
                    break;
                case 112:
                    a[0]+=321;
                    a[1]+=291;
                    break;
                case 113:
                    a[0]+=362;
                    a[1]+=301;
                    break;
                case 114:
                    a[0]+=398;
                    a[1]+=303;
                    break;
                case 115:
                    a[0]+=461;
                    a[1]+=279;
                    break;
                case 116:
                    a[0]+=550;
                    a[1]+=308;
                    break;
                case 117:
                    a[0]+=597;
                    a[1]+=313;
                    break;
                case 118:
                    a[0]+=553;
                    a[1]+=341;
                    break;
                case 119:
                    a[0]+=712;
                    a[1]+=328;
                    break;
                case 120:
                    a[0]+=29;
                    a[1]+=352;
                    break;
                case 121:
                    a[0]+=65;
                    a[1]+=354;
                    break;
                case 122:
                    a[0]+=101;
                    a[1]+=350;
                    break;
                case 123:
                    a[0]+=189;
                    a[1]+=349;
                    break;
                case 124:
                    a[0]+=242;
                    a[1]+=333;
                    break;
                case 125:
                    a[0]+=330;
                    a[1]+=312;
                    break;
                case 126:
                    a[0]+=432;
                    a[1]+=312;
                    break;
                case 127:
                    a[0]+=494;
                    a[1]+=329;
                    break;
                case 128:
                    a[0]+=584;
                    a[1]+=442;
                    break;
                case 129:
                    a[0]+=598;
                    a[1]+=349;
                    break;
                case 130:
                    a[0]+=309;
                    a[1]+=340;
                    break;
                case 131:
                    a[0]+=350;
                    a[1]+=331;
                    break;
                case 132:
                    a[0]+=392;
                    a[1]+=328;
                    break;
                case 133:
                    a[0]+=462;
                    a[1]+=369;
                    break;
                case 134:
                    a[0]+=514;
                    a[1]+=357;
                    break;
                case 135:
                    a[0]+=637;
                    a[1]+=374;
                    break;
                case 136:
                    a[0]+=682;
                    a[1]+=389;
                    break;
                case 137:
                    a[0]+=160;
                    a[1]+=371;
                    break;
                case 138:
                    a[0]+=252;
                    a[1]+=357;
                    break;
                case 139:
                    a[0]+=320;
                    a[1]+=366;
                    break;
                case 140:
                    a[0]+=385;
                    a[1]+=361;
                    break;
                case 141:
                    a[0]+=487;
                    a[1]+=392;
                    break;
                case 142:
                    a[0]+=555;
                    a[1]+=404;
                    break;
                case 143:
                    a[0]+=608;
                    a[1]+=409;
                    break;
                case 144:
                    a[0]+=40;
                    a[1]+=398;
                    break;
                case 145:
                    a[0]+=74;
                    a[1]+=397;
                    break;
                case 146:
                    a[0]+=105;
                    a[1]+=390;
                    break;
                case 147:
                    a[0]+=142;
                    a[1]+=392;
                    break;
                case 148:
                    a[0]+=172;
                    a[1]+=410;
                    break;
                case 149:
                    a[0]+=200;
                    a[1]+=392;
                    break;
                case 150:
                    a[0]+=225;
                    a[1]+=376;
                    break;
                case 151:
                    a[0]+=249;
                    a[1]+=396;
                    break;
                case 152:
                    a[0]+=274;
                    a[1]+=376;
                    break;
                case 153:
                    a[0]+=295;
                    a[1]+=404;
                    break;
                case 154:
                    a[0]+=345;
                    a[1]+=387;
                    break;
                case 155:
                    a[0]+=367;
                    a[1]+=408;
                    break;
                case 156:
                    a[0]+=397;
                    a[1]+=421;
                    break;
                case 157:
                    a[0]+=450;
                    a[1]+=431;
                    break;
                case 158:
                    a[0]+=500;
                    a[1]+=418;
                    break;
                case 159:
                    a[0]+=511;
                    a[1]+=470;
                    break;
                case 160:
                    a[0]+=624;
                    a[1]+=442;
                    break;
                case 161:
                    a[0]+=667;
                    a[1]+=423;
                    break;
                case 162:
                    a[0]+=726;
                    a[1]+=418;
                    break;
                case 163:
                    a[0]+=109;
                    a[1]+=423;
                    break;
                case 164:
                    a[0]+=149;
                    a[1]+=436;
                    break;
                case 165:
                    a[0]+=209;
                    a[1]+=433;
                    break;
                case 166:
                    a[0]+=289;
                    a[1]+=433;
                    break;
                case 167:
                    a[0]+=331;
                    a[1]+=438;
                    break;
                case 168:
                    a[0]+=357;
                    a[1]+=458;
                    break;
                case 169:
                    a[0]+=392;
                    a[1]+=449;
                    break;
                case 170:
                    a[0]+=452;
                    a[1]+=470;
                    break;
                case 171:
                    a[0]+=647;
                    a[1]+=513;
                    break;
                case 172:
                    a[0]+=560;
                    a[1]+=469;
                    break;
                case 173:
                    a[0]+=636;
                    a[1]+=480;
                    break;
                case 174:
                    a[0]+=681;
                    a[1]+=454;
                    break;
                case 175:
                    a[0]+=706;
                    a[1]+=485;
                    break;
                case 176:
                    a[0]+=41;
                    a[1]+=458;
                    break;
                case 177:
                    a[0]+=75;
                    a[1]+=444;
                    break;
                case 178:
                    a[0]+=125;
                    a[1]+=460;
                    break;
                case 179:
                    a[0]+=179;
                    a[1]+=460;
                    break;
                case 180:
                    a[0]+=233;
                    a[1]+=483;
                    break;
                case 181:
                    a[0]+=276;
                    a[1]+=466;
                    break;
                case 182:
                    a[0]+=295;
                    a[1]+=489;
                    break;
                case 183:
                    a[0]+=314;
                    a[1]+=460;
                    break;
                case 184:
                    a[0]+=390;
                    a[1]+=482;
                    break;
                case 185:
                    a[0]+=444;
                    a[1]+=521;
                    break;
                case 186:
                    a[0]+=483;
                    a[1]+=501;
                    break;
                case 187:
                    a[0]+=540;
                    a[1]+=509;
                    break;
                case 188:
                    a[0]+=599;
                    a[1]+=500;
                    break;
                case 189:
                    a[0]+=76;
                    a[1]+=503;
                    break;
                case 190:
                    a[0]+=121;
                    a[1]+=528;
                    break;
                case 191:
                    a[0]+=143;
                    a[1]+=499;
                    break;
                case 192:
                    a[0]+=193;
                    a[1]+=548;
                    break;
                case 193:
                    a[0]+=259;
                    a[1]+=509;
                    break;
                case 194:
                    a[0]+=274;
                    a[1]+=534;
                    break;
                case 195:
                    a[0]+=315;
                    a[1]+=527;
                    break;
                case 196:
                    a[0]+=341;
                    a[1]+=487;
                    break;
                case 197:
                    a[0]+=352;
                    a[1]+=513;
                    break;
                case 198:
                    a[0]+=514;
                    a[1]+=534;
                    break;
                case 199:
                    a[0]+=599;
                    a[1]+=533;
                    break;             
                default:
                    throw new IllegalArgumentException("Illegal Field ID Error");
            }
            return a;
            
        }
    }
}
