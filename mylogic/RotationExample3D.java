package mylogic;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import java.awt.*;
import javax.swing.JFrame;

import com.jogamp.opengl.util.FPSAnimator;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class RotationExample3D implements GLEventListener {

    public static DisplayMode dm, dm_old;
    private GLU glu = new GLU();
    static float rquad = 0.0f,squad=0.0f,qquad=0.0f;//rotation angle
    static  float sf=1.0f;//scaling factor
    public ArrayList<Cuboid> drawCuboids;//all cuboids to draw is stored in this list
    public Cuboid bin;//container

    public void display( GLAutoDrawable drawable ) {

        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
        gl.glLoadIdentity();

        gl.glEnable(GL.GL_MULTISAMPLE);
        //Performs transformations
        gl.glTranslatef(0.0f, 0.0f, -5.0f );
        gl.glRotatef(rquad, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(squad, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(qquad, 0.0f, 0.0f,1.0f);
        gl.glScalef(sf,sf,sf);

        gl.glPointSize(10.0f);
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex3d(0.0,0.0,0.0);
        gl.glEnd();

        gl.glPointSize(10.0f);
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex3d(new Double(bin.length),new Double(bin.height),new Double(bin.breadth));
        gl.glEnd();

        gl.glColor3f(0f,0f,0f); //black color for container border
        //This code draws the container edges
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.25f, 0.0f, 0.0f);
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(1.25f, 0.0f, 0.0f);
        gl.glVertex3f(1.25f, 0.0f, 1.25f);
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(1.25f, 0.0f, 1.25f);
        gl.glVertex3f(0.0f, 0.0f, 1.25f);
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(0.0f, 0.0f, 1.25f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f,1.25f, 0.0f);
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(0.0f,1.25f, 0.0f);
        gl.glVertex3f(1.25f, 1.25f, 0.0f);
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(1.25f, 1.25f, 0.0f);
        gl.glVertex3f(1.25f, 1.25f,1.25f);
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(1.25f, 1.25f,1.25f);
        gl.glVertex3f(0.0f, 1.25f,1.25f);
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(0.0f, 1.25f,1.25f);
        gl.glVertex3f(0.0f, 1.25f,0.0f);
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(0.0f, 1.25f,1.25f);
        gl.glVertex3f(0.0f,0.0f,1.25f);
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(1.25f, 1.25f,1.25f);
        gl.glVertex3f(1.25f, 0.0f,1.25f);
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(1.25f, 1.25f,0.0f);
        gl.glVertex3f(1.25f, 0.0f,0.0f);
        gl.glEnd();

        for(int i=1;i<drawCuboids.size();i++){
        //Next cuboid is taken
            Cuboid curr=drawCuboids.get(i);

            gl.glColor3f(curr.color[0],curr.color[1],curr.color[2]);

            gl.glBegin(GL2.GL_QUADS);//TOP
            gl.glVertex3d(new Double(curr.topLeftRear.x)*(1.25/new Double(bin.length)),new Double(curr.topLeftRear.y)*(1.25/new Double(bin.height)),new Double(curr.topLeftRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.topRightRear.x)*(1.25/new Double(bin.length)),new Double(curr.topRightRear.y)*(1.25/new Double(bin.height)),new Double(curr.topRightRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.topRightFront.x) * (1.25 / new Double(bin.length)), new Double(curr.topRightFront.y) * (1.25 / new Double(bin.height)), new Double(curr.topRightFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.topLeftFront.x) * (1.25 / new Double(bin.length)), new Double(curr.topLeftFront.y) * (1.25 / new Double(bin.height)), new Double(curr.topLeftFront.z) * (1.25 / new Double(bin.breadth)));

            gl.glEnd();

            gl.glBegin(GL2.GL_QUADS);//BOTTOM
            gl.glVertex3d(new Double(curr.bottomLeftRear.x)*(1.25/new Double(bin.length)),new Double(curr.bottomLeftRear.y)*(1.25/new Double(bin.height)),new Double(curr.bottomLeftRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomRightRear.x)*(1.25/new Double(bin.length)),new Double(curr.bottomRightRear.y)*(1.25/new Double(bin.height)),new Double(curr.bottomRightRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomRightFront.x) * (1.25 / new Double(bin.length)), new Double(curr.bottomRightFront.y) * (1.25 / new Double(bin.height)), new Double(curr.bottomRightFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomLeftFront.x) * (1.25 / new Double(bin.length)), new Double(curr.bottomLeftFront.y) * (1.25 / new Double(bin.height)), new Double(curr.bottomLeftFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glEnd();

            gl.glBegin(GL2.GL_QUADS);//LEFT
            gl.glVertex3d(new Double(curr.topLeftFront.x) * (1.25 / new Double(bin.length)), new Double(curr.topLeftFront.y) * (1.25 / new Double(bin.height)), new Double(curr.topLeftFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.topLeftRear.x)*(1.25/new Double(bin.length)),new Double(curr.topLeftRear.y)*(1.25/new Double(bin.height)),new Double(curr.topLeftRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomLeftRear.x)*(1.25/new Double(bin.length)),new Double(curr.bottomLeftRear.y)*(1.25/new Double(bin.height)),new Double(curr.bottomLeftRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomLeftFront.x) * (1.25 / new Double(bin.length)), new Double(curr.bottomLeftFront.y) * (1.25 / new Double(bin.height)), new Double(curr.bottomLeftFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glEnd();

            gl.glBegin(GL2.GL_QUADS);//RIGHT
            gl.glVertex3d(new Double(curr.topRightFront.x) * (1.25 / new Double(bin.length)), new Double(curr.topRightFront.y) * (1.25 / new Double(bin.height)), new Double(curr.topRightFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.topRightRear.x)*(1.25/new Double(bin.length)),new Double(curr.topRightRear.y)*(1.25/new Double(bin.height)),new Double(curr.topRightRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomRightRear.x)*(1.25/new Double(bin.length)),new Double(curr.bottomRightRear.y)*(1.25/new Double(bin.height)),new Double(curr.bottomRightRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomRightFront.x) * (1.25 / new Double(bin.length)), new Double(curr.bottomRightFront.y) * (1.25 / new Double(bin.height)), new Double(curr.bottomRightFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glEnd();

            gl.glBegin(GL2.GL_QUADS);//FRONT
            gl.glVertex3d(new Double(curr.topLeftFront.x) * (1.25 / new Double(bin.length)), new Double(curr.topLeftFront.y) * (1.25 / new Double(bin.height)), new Double(curr.topLeftFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.topRightFront.x) * (1.25 / new Double(bin.length)), new Double(curr.topRightFront.y) * (1.25 / new Double(bin.height)), new Double(curr.topRightFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomRightFront.x) * (1.25 / new Double(bin.length)), new Double(curr.bottomRightFront.y) * (1.25 / new Double(bin.height)), new Double(curr.bottomRightFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomLeftFront.x) * (1.25 / new Double(bin.length)), new Double(curr.bottomLeftFront.y) * (1.25 / new Double(bin.height)), new Double(curr.bottomLeftFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glEnd();

            gl.glBegin(GL2.GL_QUADS);//REAR
            gl.glVertex3d(new Double(curr.topLeftRear.x)*(1.25/new Double(bin.length)),new Double(curr.topLeftRear.y)*(1.25/new Double(bin.height)),new Double(curr.topLeftRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.topRightRear.x)*(1.25/new Double(bin.length)),new Double(curr.topRightRear.y)*(1.25/new Double(bin.height)),new Double(curr.topRightRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomRightRear.x)*(1.25/new Double(bin.length)),new Double(curr.bottomRightRear.y)*(1.25/new Double(bin.height)),new Double(curr.bottomRightRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomLeftRear.x)*(1.25/new Double(bin.length)),new Double(curr.bottomLeftRear.y)*(1.25/new Double(bin.height)),new Double(curr.bottomLeftRear.z)*(1.25/new Double(bin.breadth)));
            gl.glEnd();

            gl.glLineWidth(3.0f);
            gl.glColor3f(0.0f,0.0f,0.0f);//balck color
            //Edges of each cuboid is drwan
            gl.glBegin(GL2.GL_LINES);
            gl.glVertex3d(new Double(curr.topLeftRear.x)*(1.25/new Double(bin.length)),new Double(curr.topLeftRear.y)*(1.25/new Double(bin.height)),new Double(curr.topLeftRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.topRightRear.x)*(1.25/new Double(bin.length)),new Double(curr.topRightRear.y)*(1.25/new Double(bin.height)),new Double(curr.topRightRear.z)*(1.25/new Double(bin.breadth)));
            gl.glEnd();

            gl.glBegin(GL2.GL_LINES);
            gl.glVertex3d(new Double(curr.topRightRear.x)*(1.25/new Double(bin.length)),new Double(curr.topRightRear.y)*(1.25/new Double(bin.height)),new Double(curr.topRightRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.topRightFront.x) * (1.25 / new Double(bin.length)), new Double(curr.topRightFront.y) * (1.25 / new Double(bin.height)), new Double(curr.topRightFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glEnd();

            gl.glBegin(GL2.GL_LINES);
            gl.glVertex3d(new Double(curr.topRightFront.x) * (1.25 / new Double(bin.length)), new Double(curr.topRightFront.y) * (1.25 / new Double(bin.height)), new Double(curr.topRightFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.topLeftFront.x) * (1.25 / new Double(bin.length)), new Double(curr.topLeftFront.y) * (1.25 / new Double(bin.height)), new Double(curr.topLeftFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glEnd();

            gl.glBegin(GL2.GL_LINES);
            gl.glVertex3d(new Double(curr.topLeftRear.x)*(1.25/new Double(bin.length)),new Double(curr.topLeftRear.y)*(1.25/new Double(bin.height)),new Double(curr.topLeftRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.topLeftFront.x) * (1.25 / new Double(bin.length)), new Double(curr.topLeftFront.y) * (1.25 / new Double(bin.height)), new Double(curr.topLeftFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glEnd();

            gl.glBegin(GL2.GL_LINES);
            gl.glVertex3d(new Double(curr.bottomLeftRear.x)*(1.25/new Double(bin.length)),new Double(curr.bottomLeftRear.y)*(1.25/new Double(bin.height)),new Double(curr.bottomLeftRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomRightRear.x)*(1.25/new Double(bin.length)),new Double(curr.bottomRightRear.y)*(1.25/new Double(bin.height)),new Double(curr.bottomRightRear.z)*(1.25/new Double(bin.breadth)));
            gl.glEnd();

            gl.glBegin(GL2.GL_LINES);
            gl.glVertex3d(new Double(curr.bottomRightRear.x)*(1.25/new Double(bin.length)),new Double(curr.bottomRightRear.y)*(1.25/new Double(bin.height)),new Double(curr.bottomRightRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomRightFront.x) * (1.25 / new Double(bin.length)), new Double(curr.bottomRightFront.y) * (1.25 / new Double(bin.height)), new Double(curr.bottomRightFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glEnd();

            gl.glBegin(GL2.GL_LINES);
            gl.glVertex3d(new Double(curr.bottomRightFront.x) * (1.25 / new Double(bin.length)), new Double(curr.bottomRightFront.y) * (1.25 / new Double(bin.height)), new Double(curr.bottomRightFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomLeftFront.x) * (1.25 / new Double(bin.length)), new Double(curr.bottomLeftFront.y) * (1.25 / new Double(bin.height)), new Double(curr.bottomLeftFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glEnd();

            gl.glBegin(GL2.GL_LINES);
            gl.glVertex3d(new Double(curr.bottomLeftRear.x)*(1.25/new Double(bin.length)),new Double(curr.bottomLeftRear.y)*(1.25/new Double(bin.height)),new Double(curr.bottomLeftRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomLeftFront.x) * (1.25 / new Double(bin.length)), new Double(curr.bottomLeftFront.y) * (1.25 / new Double(bin.height)), new Double(curr.bottomLeftFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glEnd();

            gl.glBegin(GL2.GL_LINES);
            gl.glVertex3d(new Double(curr.topLeftFront.x) * (1.25 / new Double(bin.length)), new Double(curr.topLeftFront.y) * (1.25 / new Double(bin.height)), new Double(curr.topLeftFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomLeftFront.x) * (1.25 / new Double(bin.length)), new Double(curr.bottomLeftFront.y) * (1.25 / new Double(bin.height)), new Double(curr.bottomLeftFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glEnd();

            gl.glBegin(GL2.GL_LINES);
            gl.glVertex3d(new Double(curr.topRightFront.x) * (1.25 / new Double(bin.length)), new Double(curr.topRightFront.y) * (1.25 / new Double(bin.height)), new Double(curr.topRightFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomRightFront.x) * (1.25 / new Double(bin.length)), new Double(curr.bottomRightFront.y) * (1.25 / new Double(bin.height)), new Double(curr.bottomRightFront.z) * (1.25 / new Double(bin.breadth)));
            gl.glEnd();

            gl.glBegin(GL2.GL_LINES);
            gl.glVertex3d(new Double(curr.topLeftRear.x)*(1.25/new Double(bin.length)),new Double(curr.topLeftRear.y)*(1.25/new Double(bin.height)),new Double(curr.topLeftRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomLeftRear.x)*(1.25/new Double(bin.length)),new Double(curr.bottomLeftRear.y)*(1.25/new Double(bin.height)),new Double(curr.bottomLeftRear.z)*(1.25/new Double(bin.breadth)));
            gl.glEnd();

            gl.glBegin(GL2.GL_LINES);
            gl.glVertex3d(new Double(curr.topRightRear.x)*(1.25/new Double(bin.length)),new Double(curr.topRightRear.y)*(1.25/new Double(bin.height)),new Double(curr.topRightRear.z)*(1.25/new Double(bin.breadth)));
            gl.glVertex3d(new Double(curr.bottomRightRear.x)*(1.25/new Double(bin.length)),new Double(curr.bottomRightRear.y)*(1.25/new Double(bin.height)),new Double(curr.bottomRightRear.z)*(1.25/new Double(bin.breadth)));
            gl.glEnd();

        }

        gl.glFlush();

    }

    public void dispose( GLAutoDrawable drawable ) {
        // TODO Auto-generated method stub
    }

    public void init( GLAutoDrawable drawable ) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel( GL2.GL_SMOOTH );
        gl.glClearColor( 1f, 1f, 1f, 0f );
        gl.glClearDepth( 1.0f );
        gl.glEnable( GL2.GL_DEPTH_TEST );
        gl.glDepthFunc( GL2.GL_LEQUAL );
        gl.glHint( GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST );
        ThreeDPacking packing=new ThreeDPacking();
        drawCuboids=packing.cuboidArrayList;
        bin=drawCuboids.get(0);
    }


    public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {

        // TODO Auto-generated method stub
        final GL2 gl = drawable.getGL().getGL2();
        if( height <= 0 )
            height = 1;

        final float h = ( float ) width / ( float ) height;
        gl.glViewport( 0, 0, width, height );
        gl.glMatrixMode( GL2.GL_PROJECTION );
        gl.glLoadIdentity();

        glu.gluPerspective( 45.0f, h, 1.0, 20.0 );
        gl.glMatrixMode( GL2.GL_MODELVIEW );
        gl.glLoadIdentity();
    }

    public static void main( String[] args ) {

        final GLProfile profile = GLProfile.get( GLProfile.GL2 );
        GLCapabilities capabilities = new GLCapabilities( profile );

        // The canvas
        final GLCanvas glcanvas = new GLCanvas( capabilities );
        RotationExample3D cube = new RotationExample3D();

        glcanvas.addGLEventListener( cube );
        glcanvas.setSize( 1250, 750 );
        //Mouse event is used for scaling
        glcanvas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton()==MouseEvent.BUTTON1)
                    sf-=0.1f;
                else
                    sf+=0.1f;
            }
        }) ;
        //Keyboard event is used for rotation
        glcanvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyChar()=='w')
                    rquad -= 5f;
                else if(e.getKeyChar()=='s')
                    rquad+=5f;
                else if(e.getKeyChar()=='a')
                    squad-=5f;
                else if(e.getKeyChar()=='d')
                    squad+=5f;
                else if(e.getKeyChar()=='e')
                    qquad-=5f;
                else if(e.getKeyChar()=='q')
                    qquad+=5f;

            }
        });

        final JFrame frame = new JFrame ( "3D Packing" );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add( glcanvas );
        frame.setSize( frame.getContentPane().getPreferredSize() );
        frame.setVisible( true );
        final FPSAnimator animator = new FPSAnimator(glcanvas,10,true);
        animator.start();
    }
}