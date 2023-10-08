import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class RAG extends JPanel implements KeyListener, FocusListener {
	private static final long serialVersionUID = 1L;

	 static public char c;
	 static public boolean focus;
	 
	 static BufferedImage invcursimg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	 static Cursor invcurs = Toolkit.getDefaultToolkit().createCustomCursor(invcursimg, new Point(0, 0), "");
	 
	 static final JFrame frame = new JFrame("Press ESC to stop, click to resume");
	 static final JLabel label = new JLabel();
	 
	 static int centralx, centraly;
	 
	 static boolean holdw=false;
	 static boolean holdq=false;
	 static boolean holde=false;
	 static boolean holda=false;
	 static boolean holds=false;
	 static boolean holdd=false;
	 static boolean holdr=false;
	 static boolean holdf=false;
	 static boolean holdz=false;
	 static boolean holdx=false;
	 static boolean holdc=false;
	 static boolean holdv=false;
	 static boolean holdb=false;
	 static boolean holdm=false;
	 static boolean holdn=false;
	 static boolean holdk=false;
	 static boolean holdl=false;
	 static boolean resetcm2=false;
	 static boolean resetcm=false;
	 static boolean resettot=false;
	 
	 static public int n=3; //dimension
	 public static int h=7; //max degree
	 static public int k=5; //number of monomials
	 
	 public static int m=5; //solver precision
	 
	 public static String file = "C:\\test\\neon.png";
	 public static int res=545;

		
	 public static float delta=(float)Math.pow(2, -1*m);
	 public static int max=(int)Math.pow(2, m);

	 public static void main(String[] args) throws AWTException, InterruptedException {
		
		final int height=180;
		final int width=320;
		
		float dist=(float)1;
		float sqsz=(float)0.01;
		
		byte[][][] texture = new byte[res+2][res+2][3];
		settexture(texture);
		
		
		float ans,ftmp,ftmp2,ftmpmax=0;
		float[] ansv = new float[n];
		
		int i,j,pw,ph,l,dtmp,p;
		float[] tmp = new float[h];
		float[] tmp2 = new float[h];
		int[][] mon = new int[k][n];
		float[] coef = new float[k];
		
		int[][][] mond1= new int[n][k][n];
		int[][][] mond2= new int[n][k][n];
		float[][] coefd1 = new float[n][k];
		float[][] coefd2 = new float[n][k];
		
		/*
		//3,3,4
		mon[1][0]=2;
		mon[2][1]=2;
		mon[3][2]=2;
		
		coef[0]=-0.1f;
		coef[1]=1f;
		coef[2]=1f;
		coef[3]=1f;
		*/
		//3,4,5
				mon[1][0]=2;
				mon[2][1]=2;
				mon[3][2]=2;
				mon[4][0]=1;
				mon[4][1]=1;
				mon[4][2]=1;
				
				coef[0]=-0.1f;
				coef[1]=1f;
				coef[2]=1f;
				coef[3]=1f;
				coef[4]=5f;
		
		/*
		//5,5,4
		mon[0][1]=2;
		mon[0][2]=1;
		
		mon[1][0]=3;
		mon[2][0]=1;
		mon[2][2]=2;
		mon[2][3]=1;
		mon[3][2]=3;
		mon[3][4]=1;
		
		
		coef[0]=1f;
		coef[1]=1f;
		coef[2]=1f;
		coef[3]=1f;
*/
		/*
		//6,6,6
		mon[0][0]=3;
		mon[0][4]=1;
		mon[1][0]=2;
		mon[1][1]=1;
		mon[2][0]=1;
		mon[2][2]=1;
		mon[3][3]=1;
		mon[4][0]=4;
		mon[4][5]=1;
		mon[5][0]=5;
		
		coef[0]=1f;
		coef[1]=1f;
		coef[2]=1f;
		coef[3]=1f;
		coef[4]=1f;
		coef[5]=1f;
		*/
	
		set(mon,  coef,  mond1, mond2,  coefd1, coefd2);
		
	
	
		int currentpix;
		
		focus=true;
		
		final Robot robot = new Robot();
	    
	    
	    BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
	    
	    float[] uvtmp = new float[2];
	    byte[] tmpcol = new byte[3];
	    
		float msqsz=-sqsz;
		float multy=((float)(1-width))*sqsz/(float)2;
		float multz=((float)(height-1))*sqsz/(float)2;
		
		float[] addy = new float[n];
		float[] addz = new float[n];
		float[] vectmp = new float[n];
		float[] vecn = new float[n];
		float[][] vecl = new float[height][width];
		

			
		float[] pos = new float[n];
		
		byte[] tmpcor = new byte[3];
		float[] tmpnorm = new float[n];
		
		int mousx=0;
		int mousy=0;
		
	
		
		float[][] x = new float[n][n];
		
		float[] tmpvec = new float[n];
		
	
		
		float[][] newx = new float[n][n];
		
		float[] vec = new float[n];
	
		int tmpm=0;
		
		float rsin = (float)Math.sin(Math.PI/32.0);
		float rcos = (float)Math.cos(Math.PI/32.0);
	
		

		for(i=0;i<n;i++) x[i][i]=1f;

		

		for(i=0;i<n;i++)
		{
			vec[i]=dist*x[0][i]+multy*x[1][i]+multz*x[2][i];
			addy[i]=sqsz*x[1][i];
			addz[i]=msqsz*x[2][i];
			pos[i]=0f;
		}
		
		for(ph=0;ph<height;ph++)
		{
			for(i=0;i<n;i++) vectmp[i]=vec[i];
			for(pw=0;pw<width;pw++)
			{
				vecl[ph][pw]=1f/veclgt(vec);
				for(i=0;i<n;i++) vec[i]+=addy[i];
			}
			for(i=0;i<n;i++) vec[i]=vectmp[i]+addz[i];
		}
	  
	    frame.setResizable(false);
	    frame.getContentPane().add(new RAG());
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.setSize(width, height);
	    
	    frame.getContentPane().addMouseListener(new MouseAdapter() {            
	    	   @Override
	    	   public void mouseClicked(MouseEvent e) {
	    		  
	    	      focus=true;
	    	      centralx=frame.getX()+(width/2);
	    	      centraly=frame.getY()+(height/2);
	    	      frame.getContentPane().setCursor(invcurs);
	    	   }
	    	});
	    
	    frame.getContentPane().setCursor(invcurs);
	    frame.getContentPane().setBackground( Color.BLACK );
	    
	    final byte[] pixels =((DataBufferByte) image.getRaster().getDataBuffer()).getData();

       label.setIcon(new ImageIcon(image));
       frame.getContentPane().add(label,BorderLayout.CENTER);
       frame.setLocationRelativeTo(null);
       frame.pack();
       frame.setVisible(true);
	   
       centralx=frame.getX()+(width/2);
       centraly=frame.getY()+(height/2);
       
       
	    while(true)
	    {
	    	Thread.sleep(10);
	    	
	    	if(focus)
	    	{
	    		if(holdl)
	    		{
	    			holdl=false;
	    			for(i=0;i<k;i++)
	    			{
	    				dtmp=0;
	    				for(j=0;j<n;j++) dtmp+=mon[i][j];
	    				coef[i]*=(float)Math.pow(1.1f, dtmp);
	    			}
	    		}
			    	if(resetcm)
			    	{
			    		for(i=0;i<n;i++) for(j=0;j<n;j++) if(i==j) x[i][j]=1; else x[i][j]=0;
			    		resetcm=false;
			    	}
			    	if(resetcm2)
			    	{
			    		for(i=0;i<n;i++) for(j=0;j<n;j++) if(i==j) x[i][j]=1; else x[i][j]=0;
			    		for(i=0;i<n;i++) x[0][i]=1f/(float)Math.sqrt(n);
			    		for(i=1;i<n;i++)
			    		{
			    			for(j=i-1;j>=0;j--)
			    			{
			    				x[i]= vecsub(x[i],svec(x[j],vecprod(x[i],x[j])));
			    			}
			    			x[i]=vecnorm(x[i]);
			    		}
			    		resetcm2=false;
			    	}
			    	if(resettot)
			    	{
			    		for(i=0;i<n;i++) {pos[i]=0.5f; for(j=0;j<n;j++) if(i==j) x[i][j]=1; else x[i][j]=0;}
			    		resettot=false;
			    	}
		    		if(holdw)
		    		{
		    			 for(i=0;i<n;i++) pos[i]+=x[0][i]/100.0;
		    		}
		    		if(holds)
		    		{
		    			for(i=0;i<n;i++) pos[i]-=x[0][i]/100.0;
		    		}
		    		if(holda)
		    		{
		    			 for(i=0;i<n;i++) pos[i]-=x[1][i]/100.0;
		    		}
		    		if(holdd) 
		    		{
		    			for(i=0;i<n;i++) pos[i]+=x[1][i]/100.0;
		    		}
		    		
		    		
		    		
		    		mousx=MouseInfo.getPointerInfo().getLocation().x-centralx;
		    		mousy=MouseInfo.getPointerInfo().getLocation().y-centraly;
		    		
		    		if(mousx>0)
		    		{
		    			
		    			if(holdz || holdx || holdc || holdv || holdb || holdn || holdm || holdk || holdl)
		    			{
		    				//
		    			}
		    			else
		    			{
			    			for(i=0;i<n;i++)
			    			{
			    				newx[0][i]=x[0][i]*rcos+rsin*x[1][i];
			    				newx[1][i]=x[1][i]*rcos-rsin*x[0][i];
			    				x[0][i]=newx[0][i];
			    				x[1][i]=newx[1][i];
			    			}
		    			}
		    		}
		    		else if(mousx<0)
		    		{
		    			
		    			if(holdz || holdx || holdc || holdv || holdb || holdn || holdm || holdk || holdl)
		    			{
		    				//
		    			}
		    			else
		    			{
			    			for(i=0;i<n;i++)
			    			{
			    				newx[0][i]=x[0][i]*rcos-rsin*x[1][i];
			    				newx[1][i]=x[1][i]*rcos+rsin*x[0][i];
			    				x[0][i]=newx[0][i];
			    				x[1][i]=newx[1][i];
			    			}
		    			}
		    		}
		    		
		    		if(mousy<0)
		    		{
		    			if(holdz) tmpm=3;
		    			else if(holdx) tmpm=4;
		    			else if(holdc) tmpm=5;
		    			else if(holdv) tmpm=6;
		    			else if(holdb) tmpm=7;
		    			else if(holdn) tmpm=8;
		    			else if(holdm) tmpm=9;
		    			else if(holdk) tmpm=10;
		    			//else if(holdl) tmpm=11;
		    			else tmpm=2;
		    		
			    			for(i=0;i<n;i++)
			    			{
			    				newx[0][i]=x[0][i]*rcos+rsin*x[tmpm][i];
			    				newx[tmpm][i]=x[tmpm][i]*rcos-rsin*x[0][i];
			    				x[0][i]=newx[0][i];
			    				x[tmpm][i]=newx[tmpm][i];
			    			}
		    			
		    		}
		    		else if(mousy>0)
		    		{
		    			if(holdz) tmpm=3;
		    			else if(holdx) tmpm=4;
		    			else if(holdc) tmpm=5;
		    			else if(holdv) tmpm=6;
		    			else if(holdb) tmpm=7;
		    			else if(holdn) tmpm=8;
		    			else if(holdm) tmpm=9;
		    			else if(holdk) tmpm=10;
		    			//else if(holdl) tmpm=11;
		    			else tmpm=2;
		    			
			    			for(i=0;i<n;i++)
			    			{
			    				newx[0][i]=x[0][i]*rcos-rsin*x[tmpm][i];
			    				newx[tmpm][i]=x[tmpm][i]*rcos+rsin*x[0][i];
			    				x[0][i]=newx[0][i];
			    				x[tmpm][i]=newx[tmpm][i];
			    			}
		    			
		    		}
			    	 
			    	currentpix=0;
			    	for(i=0;i<n;i++)
			    	{
			    		vec[i]=dist*x[0][i]+multy*x[1][i]+multz*x[2][i];
			    		addy[i]=sqsz*x[1][i];
			    		addz[i]=msqsz*x[2][i];
			    	}
			   
				    	
			 	    for(ph=0;ph<height;ph++)
			 	    {
			 	    	for(i=0;i<n;i++) vectmp[i]=vec[i];
			 	    	for(pw=0;pw<width;pw++)
			 	    	{
		
			 	    		for(i=0;i<n;i++)
			 				{
			 					vecn[i]=vec[i]*vecl[ph][pw];
			 				}
			 	    		
			 	    		////
			 	    		
			 	    		for(i=0;i<h;i++) tmp[i]=0f;
			 	   		for(i=0;i<k;i++)
			 	   		{
			 	   			if(coef[i]!=0)
			 	   			{
			 	   				dtmp=0;
			 	   				tmp2[0]=coef[i];
			 	   				for(j=1;j<h;j++) tmp2[j]=0f;
			 	   				
			 	   				for(j=0;j<n;j++)
			 	   				{
			 	   					for(l=0;l<mon[i][j];l++)
			 	   					{
			 	   						mult(tmp2,dtmp,pos[j],vecn[j]);
			 	   						dtmp++;
			 	   					}
			 	   				}
			 	   				sum(tmp,tmp2);
			 	   			}
			 	   		}
			 	   		
			 	   		ans=solve(tmp);
			 	   		for(i=0;i<n;i++) ansv[i]=pos[i]+ans*vecn[i];
			 	   				
			 	   		/*	
			 	   		//norm
		 	   			for(p=0;p<n;p++) {
					 	   	ftmp=0f;
			 	   			for(i=0;i<k;i++)
				 	   		{
				 	   			if(coefd1[p][i]!=0)
				 	   			{
				 	   				ftmp2=coefd1[p][i];
				 	   				
				 	   				for(j=0;j<n;j++)
				 	   				{
				 	   					for(l=0;l<mond1[p][i][j];l++)
				 	   					{
				 	   						ftmp2*=ansv[j];
				 	   					}
				 	   				}
				 	   				ftmp+=ftmp2;
				 	   			}
				 	   		}
			 	   			tmpnorm[p]=ftmp;
		 	   			}
		 	   			tmpnorm=vecnorm(tmpnorm);
		 	   			
		 	   			//texture
		 	   			uvtmp=uvmap(tmpnorm[0],tmpnorm[1],tmpnorm[2]);
		 	   			//
		 	   			//tmpvec=vecmir(vecn,tmpnorm);
		 	   			//uvtmp=uvmap(tmpvec[0],tmpvec[1],tmpvec[2]);
		 	   			
		 	   			
			 	   		if(ans==0f) pixels[currentpix]=(byte)0;
		 	    		else {pixels[currentpix]=(byte)(255-255*ans);}
		 	    		currentpix++;
		 	    		pixels[currentpix]=texture[(int)(res*uvtmp[0])][(int)(res*uvtmp[1])][2];
		 	    		currentpix++;
		 	    		pixels[currentpix]=texture[(int)(res*uvtmp[0])][(int)(res*uvtmp[1])][1];
		 	    		currentpix++;
		 	    		pixels[currentpix]=texture[(int)(res*uvtmp[0])][(int)(res*uvtmp[1])][0];
		 	    		currentpix++;
		 	   			*/
		 	   			/*
		 	   			//checkerboard uv
		 	   			//
		 	   			//uvtmp=uvmap(tmpnorm[0],tmpnorm[1],tmpnorm[2]);
		 	   			//
		 	   			tmpvec=vecmir(vecn,tmpnorm);
		 	   			uvtmp=uvmap(tmpvec[0],tmpvec[1],tmpvec[2]);
		 	   			//
		 	   			
			 	   		dtmp=0;
		 	   			dtmp=(int)(16*uvtmp[0])+(int)(16*uvtmp[1]);
		 	   			
		 	   			if(dtmp%2==0)
						{
		 	   				if(ans==0f) pixels[currentpix]=(byte)0;
		 	   				else {pixels[currentpix]=(byte)(255-255*ans);}
		 	    		currentpix++;
		 	    		pixels[currentpix]=(byte)255;
		 	    		currentpix++;
		 	    		pixels[currentpix]=(byte)255;
		 	    		currentpix++;
		 	    		pixels[currentpix]=(byte)255;
		 	    		currentpix++;
						}
						else
						{
							if(ans==0f) pixels[currentpix]=(byte)0;
		 	    		else {pixels[currentpix]=(byte)(255-255*ans);}
		 	    		currentpix++;
		 	    		pixels[currentpix]=(byte)0;
		 	    		currentpix++;
		 	    		pixels[currentpix]=(byte)0;
		 	    		currentpix++;
		 	    		pixels[currentpix]=(byte)0;
		 	    		currentpix++;
						}
		 	   			*/
		 	   			
		 	   			/*
		 	   			//tmpnorm . vecn
		 	   			tmpcor=rnbw(scalprod(tmpnorm,vecn),1f);
		 	   		if(ans==0f) pixels[currentpix]=(byte)0;
	 	    		else {pixels[currentpix]=(byte)(255-255*ans);}
	 	    		currentpix++;
	 	    		pixels[currentpix]=tmpcor[0];
	 	    		currentpix++;
	 	    		pixels[currentpix]=tmpcor[1];
	 	    		currentpix++;
	 	    		pixels[currentpix]=tmpcor[2];
	 	    		currentpix++;
			 	   		*/		
			 	   			
			 	   				/*
			 	   			////laplace
			 	   			ftmp=0f;
			 	   			for(p=0;p<n;p++) {
				 	   			for(i=0;i<k;i++)
					 	   		{
					 	   			if(coefd2[p][i]!=0)
					 	   			{
					 	   				ftmp2=coefd2[p][i];
					 	   				
					 	   				for(j=0;j<n;j++)
					 	   				{
					 	   					for(l=0;l<mond2[p][i][j];l++)
					 	   					{
					 	   						ftmp2*=ansv[j];
					 	   					}
					 	   				}
					 	   				ftmp+=ftmp2;
					 	   			}
					 	   		}
			 	   			}
			 	   			
			 	   			tmpcor=rnbw(ftmp,0.4f);
			 	   		if(ans==0f) pixels[currentpix]=(byte)0;
		 	    		else {pixels[currentpix]=(byte)(255-255*ans);}
		 	    		currentpix++;
		 	    		pixels[currentpix]=tmpcor[0];
		 	    		currentpix++;
		 	    		pixels[currentpix]=tmpcor[1];
		 	    		currentpix++;
		 	    		pixels[currentpix]=tmpcor[2];
		 	    		currentpix++;
			 			*/
		 	    		
		 	    		
		 	    		
			 				    /*
			 				    //og
				 	    		if(ans==0f) pixels[currentpix]=(byte)0;
				 	    		else {pixels[currentpix]=(byte)(255-255*ans);}
				 	    		currentpix++;
				 	    		pixels[currentpix]=(byte)(255*(float)Math.sin(100*(ansv[0])));
				 	    		currentpix++;
				 	    		pixels[currentpix]=(byte)(255*(float)Math.sin(77*(ansv[1])));
				 	    		currentpix++;
				 	    		pixels[currentpix]=(byte)(255*(float)Math.sin(123*(ansv[2])));
				 	    		currentpix++;
			 				*/
			 	   			
			 	   				
			 	   				
			 	   				
			 	   			
			 	   			//checkerboard
			 	   				dtmp=0;
			 	   			for(i=0;i<n;i++) 
		 					{
		 						 dtmp+=(int)(7*ansv[i]);
		 					}
			 	   			
			 	   		if(dtmp%2==0)
	 					{
			 	   		if(ans==0f) pixels[currentpix]=(byte)0;
		 	    		else {pixels[currentpix]=(byte)(255-255*ans);}
			 	    		currentpix++;
			 	    		pixels[currentpix]=(byte)255;
			 	    		currentpix++;
			 	    		pixels[currentpix]=(byte)255;
			 	    		currentpix++;
			 	    		pixels[currentpix]=(byte)255;
			 	    		currentpix++;
	 					}
	 					else
	 					{
	 						if(ans==0f) pixels[currentpix]=(byte)0;
			 	    		else {pixels[currentpix]=(byte)(255-255*ans);}
			 	    		currentpix++;
			 	    		pixels[currentpix]=(byte)0;
			 	    		currentpix++;
			 	    		pixels[currentpix]=(byte)0;
			 	    		currentpix++;
			 	    		pixels[currentpix]=(byte)0;
			 	    		currentpix++;
	 					}
			 	   		
			 
			 	    		
			 	    		for(i=0;i<n;i++) vec[i]+=addy[i];
			 	    	}
			 	    	
			 	    	for(i=0;i<n;i++) vec[i]=vectmp[i]+addz[i];
			 	    }
			 	    

			       label.setIcon(new ImageIcon(image));
			       frame.getContentPane().add(label,BorderLayout.CENTER);
			       robot.mouseMove(centralx,centraly);
	    	}
	    }
	}
	
	public static float vecprod(float[] v1, float[] v2)
	{
		float ret=0;
		for(int i=0;i<n;i++) ret+=v1[i]*v2[i];
		return ret;
	}
	
	public static float[] svec(float[] v, float s)
	{
		float[] ret = new float[n];
		for(int i=0;i<n;i++) ret[i]=v[i]*s;
		return ret;
	}
	
	public static float[] vecmir(float[] v, float[] n)
	{
		return vecsub(v,svec(n,2f*vecprod(v,n)));
	}
	
	public static float[] vecsub(float[] v1,float[] v2)
	{
		float[] ret = new float[n];
		for(int i=0;i<n;i++) ret[i]=v1[i]-v2[i];
		return ret;
	}
	
	public static float veclgt(float[] v)
	{
		return (float)Math.sqrt(vecprod(v,v));
	}
	
	public static float[] vecnorm(float[] v)
	{
		float prod = veclgt(v);
		float[] ret = new float[n];
		for(int i=0;i<n;i++) ret[i]=v[i]/prod;
		return ret;
	}
	
	public static void displayvec(int[] v)
	{
		int i;
		for(i=0;i<n-1;i++) System.out.print(v[i]+",");
		System.out.println(v[i]);
	}
	
	public static void displayvecf(float[] v)
	{
		int i;
		for(i=0;i<n-1;i++) System.out.print(v[i]+",");
		System.out.println(v[i]);
	}
	
	
	
	
	public static void sum(float[] v1, float[] v2)
	{
		for(int i=0;i<h;i++) v1[i]+=v2[i];
	}
	
	public static void smult(float[] v, float a)
	{
		for(int i=0;i<h;i++) v[i]*=a;
	}
	
	public static void mult(float[] v, int d, float a, float b)
	{
		v[d+1]=b*v[d];
		for(int i=d;i>0;i--) 
		{
			v[i]*=a;
			v[i]+=b*v[i-1];
		}
		v[0]=a*v[0];

	}
	
	public static byte[] rnbw(float x, float a)
	{
		byte[] ret = new byte[3];
		float tmp;
		
		if(x<0) x*=-1f;
		x=(x*a)%1;
		tmp=x%(1f/6f);
		
		if(x<1f/6f)
		{
			ret[0]=(byte)255;
			ret[1]=(byte)(1530f*tmp);
		}
		else if(x<1f/3f)
		{
			ret[1]=(byte)255;
			ret[0]=(byte)(255f-1530f*tmp);
		}
		else if(x<0.5f)
		{
			ret[1]=(byte)255;
			ret[2]=(byte)(1530f*tmp);
		}
		else if(x<2f/3f)
		{
			ret[2]=(byte)255;
			ret[1]=(byte)(255f-1530f*tmp);
		}
		else if(x<5f/6f)
		{
			ret[2]=(byte)255;
			ret[0]=(byte)(1530f*tmp);
		}
		else
		{
			ret[0]=(byte)255;
			ret[2]=(byte)(255f-1530f*tmp);
		}
		
		return ret;
	}
	
	public static float scalprod(float[] v1, float[] v2)
	{
		int i;
		float ret=0;
		for(i=0;i<n;i++) ret+=v1[i]*v2[i];
		return ret;
	}
			
	
	public static float solve(float[] vec)
	{
		float incr=0;
		float tmp = Math.signum(vec[0]);
		int i;
		
		for(i=0;i<max;i++)
		{
			incr+=delta;
			if(Math.signum(eval(vec,incr))!=tmp) return incr;
		}
		
		return 0;
	}
	
	public static float eval(float[] vec, float x)
	{
		int i;
		float ret=vec[0];
		float newx=x;
		
		for(i=1;i<h-1;i++)
		{
			ret+=vec[i]*newx;
			newx*=x;
		}
		
		ret+=vec[i]*newx;
		
		return ret;
	}
	
	public static void set(int[][] mon, float[] coef, int[][][] mond1, int[][][] mond2, float[][] coefd1, float[][] coefd2)
	{
		int i,j,l;
		
		for(i=0;i<n;i++)
		{
			for(j=0;j<k;j++)
			{
				if(mon[j][i]!=0)
				{
					for(l=0;l<n;l++) mond1[i][j][l]=mon[j][l];
					coefd1[i][j]=coef[j]*mon[j][i];
					mond1[i][j][i]-=1;
				}
				else coefd1[i][j]=0f;
			}
		}
		
		for(i=0;i<n;i++)
		{
			for(j=0;j<k;j++)
			{
				if(mond1[i][j][i]!=0)
				{
					for(l=0;l<n;l++) mond2[i][j][l]=mond1[i][j][l];
					coefd2[i][j]=coefd1[i][j]*mond1[i][j][i];
					mond2[i][j][i]-=1;
				}
				else coefd2[i][j]=0f;
			}
		}
	}
	
	
	public static float[] uvmap(float dx, float dy, float dz)
	{
		float[] ret = new float[2];
		
		ret[0]=(float)(0.5d+Math.atan2(dz, dx)/(2d*Math.PI));
		ret[1]=(float)(0.5d+Math.asin(dy)/Math.PI);
		
		return ret;
	}
	
	public static void settexture(byte[][][] texture)
	{
		BufferedImage img = null;
		int i,j,pixel;
		
		try {
		    img = ImageIO.read(new File(file));
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		
		for(i=0;i<res;i++)
		{
			for(j=0;j<res;j++)
			{
				pixel=img.getRGB(i, j);

				texture[i][j][0]=(byte)((pixel >> 16) & 255);
				texture[i][j][1]=(byte)((pixel >> 8) & 255);
				texture[i][j][2]=(byte)((pixel >> 0) & 255);
			}
		}
	}
	
	 
	 
	@Override
	public void focusLost(FocusEvent e) {
        focus=false;
        frame.getContentPane().setCursor(Cursor.getDefaultCursor());
    }
	
	public RAG() {
        addKeyListener(this);
        addFocusListener(this);
    }
	
	public void addNotify() {
        super.addNotify();
        requestFocus();
    }
	
	@Override
	public void keyPressed(KeyEvent e) { }
	@Override
	public void keyReleased(KeyEvent e) {
		c = e.getKeyChar();
		if(c==119) {holdw=false;}
		else if(c==97) {holda=false;}
		else if(c==115) {holds=false;}
		else if(c==100) {holdd=false;}
		else if(c==122) {holdz=false;}
		else if(c==120) {holdx=false;}
		else if(c==99) {holdc=false;}
		else if(c==118) {holdv=false;}
		else if(c==98) {holdb=false;}
		else if(c==110) {holdn=false;}
		else if(c==109) {holdm=false;}
		else if(c==107) {holdk=false;}
		else if(c==108) {holdl=false;}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		c = e.getKeyChar();
		if(c==27) { focus=false;frame.getContentPane().setCursor(Cursor.getDefaultCursor());}
		else if(c==119) {holdw=true;}
		else if(c==97) {holda=true;}
		else if(c==115) {holds=true;}
		else if(c==100) {holdd=true;}
		else if(c==121) {resetcm=true;}
		else if(c==112) {resettot=true;}
		else if(c==117) {resetcm2=true;}
		else if(c==122) {holdz=true;}
		else if(c==120) {holdx=true;}
		else if(c==99) {holdc=true;}
		else if(c==118) {holdv=true;}
		else if(c==98) {holdb=true;}
		else if(c==110) {holdn=true;}
		else if(c==109) {holdm=true;}
		else if(c==107) {holdk=true;}
		else if(c==108) {holdl=true;}
	}

	@Override
	public void focusGained(FocusEvent arg0) {}
}