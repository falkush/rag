import java.awt.AWTException;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class renderer {

	 static public char c;
	 
	 

	 
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
	 public static int h=3; //max degree
	 static public int k=4; //number of monomials
	 
	 public static int m=10; //solver precision
	 
	 public static String file = "C:\\rag\\cat.png";
	 public static int res=469;

		
	 public static double delta=Math.pow(2, -1*m);
	 public static int max=(int)Math.pow(2, m);

	 public static void main(String[] args) throws AWTException, InterruptedException, IOException {
		
		final int height=180*6;
		final int width=320*6;
		
		int startm=580;
		int finm=3000;
		
		double dist=1d;
		double sqsz=0.01d/6d;
		
		double[][] gmov = new double[finm][n];
		double[][][] gang = new double[finm][n][n];
		int[] gmod = new int[finm];
		double[][] gcoef = new double[finm][k];
		double[][] gspin = new double[finm][n];
		
		int[][][] texture = new int[res+2][res+2][3];
		settexture(texture);
		
		
		double ans,ftmp,ftmp2;
		double[] ansv = new double[n];
		
		int i,j,pw,ph,l,dtmp,p;
		double[] tmp = new double[h];
		double[] tmp2 = new double[h];
		int[][] mon = new int[k][n];
		double[] coef = new double[k];
		
		int[][][] mond1= new int[n][k][n];
		int[][][] mond2= new int[n][k][n];
		double[][] coefd1 = new double[n][k];
		double[][] coefd2 = new double[n][k];
		
    	double d;
    	double tmpang;
    	double[] newpos = new double[n];
		
		
		 //c1
		//3,3,4
		mon[1][0]=2;
		mon[2][1]=2;
		mon[3][2]=2;
		
		coef[0]=-0.1d;
		coef[1]=1d;
		coef[2]=1d;
		coef[3]=1d;
		
		
    	/*
    	////c2
		//3,4,5
		mon[1][0]=2;
		mon[2][1]=2;
		mon[3][2]=2;
		mon[4][0]=1;
		mon[4][1]=1;
		mon[4][2]=1;
		
		coef[0]=-0.1d;
		coef[1]=1d;
		coef[2]=1d;
		coef[3]=1d;
		coef[4]=0d;
		*/
    	
		/*
		//c3
		//5,5,4
		mon[0][1]=2;
		mon[0][2]=1;
		
		mon[1][0]=3;
		mon[2][0]=1;
		mon[2][2]=2;
		mon[2][3]=1;
		mon[3][2]=3;
		mon[3][4]=1;
		
		
		coef[0]=1d;
		coef[1]=1d;
		coef[2]=1d;
		coef[3]=1d;
*/
		/*
		//6,6,6
    	//c4
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
		
		coef[0]=1d;
		coef[1]=1d;
		coef[2]=1d;
		coef[3]=1d;
		coef[4]=1d;
		coef[5]=1d;
		*/
		/*
		 //c5
    	mon[1][0]=4;
		mon[2][1]=4;
		mon[3][2]=4;
		mon[4][0]=1;
		mon[4][1]=2;
		mon[4][2]=3;
		
		coef[0]=-0.01d;
		coef[1]=1d;
		coef[2]=1d;
		coef[3]=1d;
		coef[4]=0d;
		*/
		
		
		/*
		//c3m4
		for(i=0;i<1000;i++)
		{
			gmov[i][0]=-1d/500000d;
			gmod[i]=4;
		}
		for(i=1000;i<1282;i++)
		{
			gang[i][0][2]=1d/1000d;
			gmov[i][2]=1d/50000d;
			gmov[i][0]=-1d/500000d;
			gmod[i]=4;
		}
		for(i=1282;i<2000;i++)
		{
			gang[i][0][3]=1d/1000d;
			gmov[i][0]=-1d/500000d;
			gmod[i]=4;
		}
		
		for(i=2000;i<3000;i++)
		{
			gang[i][2][0]=1d/1000d;
			gmov[i][0]=-1d/500000d;
			gmod[i]=4;
		}
		//////////////////
*/
	
		
		/*
		//mov2
		gmov[0][0]=-0.7d;
				for(i=0;i<500;i++)
				{
					gspin[i][1]=true;
					gmod[i]=8;
					gcoef[i][4]=0.1d;
				}
				
				for(i=500;i<760;i++)
				{
					gmov[i][0]=0.002d;
					gmod[i]=8;
					gspin[i][1]=true;
				}
				
				for(i=760;i<1260;i++)
				{
					gmod[i]=8;
					gspin[i][1]=true;
					gspin[i][2]=true;
					gcoef[i][4]=-0.1d;
				}
		
		*/
		
		/*
		//mov3
		gmov[0][0]=-0.7d;
				for(i=0;i<500;i++)
				{
					gspin[i][1]=true;
					gmod[i]=5;
					gcoef[i][4]=0.1d;
				}
				
				for(i=500;i<760;i++)
				{
					gmov[i][0]=0.002d;
					gmod[i]=5;
					gspin[i][1]=true;
				}
				
				for(i=760;i<1000;i++)
				{
					gmod[i]=5;
					gspin[i][1]=true;
					gspin[i][2]=true;
					gcoef[i][4]=-0.1d;
				}
				for(i=1000;i<1050;i++)
				{
					gmod[i]=5;
					gmov[i][0]=0.005d;
					gcoef[i][4]=-0.1d;
				}
				
				for(i=1050;i<2000;i++)
				{
					gmod[i]=5;
					gmov[i][0]=0.005d;
				}
		*/
		
	gmov[0][0]=-0.7d;
	gmod[0]=1;
		for(i=1;i<243;i++)
		{
			gmod[i]=1;
			gspin[i][1]=0.0135347543d;
			gspin[i][2]=0.0275347521d;
			gmov[i][0]=0.004d;
		}
		
		for(i=243;i<420;i++)
		{
			gmod[i]=1;
			gspin[i][1]=0.0135347543d;
			gspin[i][2]=0.0275347521d;
			gmov[i][0]=-0.004d;
			gcoef[i][1]=0.01d;
			gcoef[i][2]=-0.01d;
		}
		for(i=420;i<597;i++)
		{
			gmod[i]=2;
			gspin[i][1]=0.0135347543d;
			gspin[i][2]=0.0275347521d;
			gmov[i][0]=0.004d;
			gcoef[i][1]=-0.01d;
			gcoef[i][2]=+0.01d;
		}
		for(i=597;i<3000;i++)
		{
			gmod[i]=2;
			gspin[i][1]=0.0135347543d;
			gspin[i][2]=0.0275347521d;
			gmov[i][0]=-0.004d;
		}
		
		
				//////////////////
				
		set(mon,  coef,  mond1, mond2,  coefd1, coefd2);
		
	
	
		int currentpix;
		
	    
	    
	    BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
	    
	    double[] uvtmp = new double[2];
	    double tmpcol;
	    double alpf;
	    
	    double tmpmin;
			double tmpf;
			double tmpf2;
	    
	    int nbframe=0;
	    
		double msqsz=-sqsz;
		double multy=((double)(1-width))*sqsz/2d;
		double multz=((double)(height-1))*sqsz/2d;
		
		double[] addy = new double[n];
		double[] addz = new double[n];
		double[] vectmp = new double[n];
		double[] vecn = new double[n];
		double[][] vecl = new double[height][width];
		

			
		double[] pos = new double[n];
		
		int[] tmpcor = new int[3];
		double[] tmpnorm = new double[n];
		
		
	
		
		double[][] x = new double[n][n];
		
		double[] tmpvec = new double[n];
		
	
		
		double[][] newx = new double[n][n];
		
		double[] vec = new double[n];
	

		
		

		for(i=0;i<n;i++) x[i][i]=1d;

		

		for(i=0;i<n;i++)
		{
			vec[i]=dist*x[0][i]+multy*x[1][i]+multz*x[2][i];
			addy[i]=sqsz*x[1][i];
			addz[i]=msqsz*x[2][i];
			pos[i]=0d;
		}

		
		for(ph=0;ph<height;ph++)
		{
			for(i=0;i<n;i++) vectmp[i]=vec[i];
			for(pw=0;pw<width;pw++)
			{
				vecl[ph][pw]=1d/veclgt(vec);
				for(i=0;i<n;i++) vec[i]+=addy[i];
			}
			for(i=0;i<n;i++) vec[i]=vectmp[i]+addz[i];
		}
	  
	
	    
	    final byte[] pixels =((DataBufferByte) image.getRaster().getDataBuffer()).getData();

	    //long start = System.currentTimeMillis();
       
	    while(nbframe<=finm)
	    {
	    	for(i=0;i<k;i++) coef[i]+=gcoef[nbframe][i];
	    	
	    	for(i=0;i<n;i++)
	    	{
	    		 for(j=0;j<n;j++) pos[j]+=x[i][j]*gmov[nbframe][i];
	    	}
	    	
	    	for(i=0;i<n;i++)
	    	{
	    		for(j=0;j<n;j++)
	    		{
	    			for(l=0;l<n;l++)
	    			{
	    				newx[i][l]=x[i][l]*Math.cos(gang[nbframe][i][j]*Math.PI)-Math.sin(gang[nbframe][i][j]*Math.PI)*x[j][l];
	    				newx[j][l]=x[j][l]*Math.cos(gang[nbframe][i][j]*Math.PI)+Math.sin(gang[nbframe][i][j]*Math.PI)*x[i][l];
	    				x[i][l]=newx[i][l];
	    				x[j][l]=newx[j][l];
	    			}
	    		}
	    	}
	    	
	    	
	    	////////////////////////////////////
	    	for(j=1;j<n;j++) {
	    		if(gspin[nbframe][j]!=0d) {
	    	d=veclgt(pos);
	    	  	

	    	
	    	tmpang=Math.PI-gspin[nbframe][j];
	    	
	    	for(i=0;i<n;i++)
	    	{
	    		newpos[i]=d*(Math.cos(tmpang)*x[0][i]+Math.sin(tmpang)*x[j][i]);
	    	}
	    	
	    	tmpang=gspin[nbframe][j];

	    	for(i=0;i<n;i++) pos[i]=newpos[i];
	    	
	    	for(l=0;l<n;l++)
			{
				newx[0][l]=x[0][l]*Math.cos(tmpang)-Math.sin(tmpang)*x[j][l];
				newx[j][l]=x[j][l]*Math.cos(tmpang)+Math.sin(tmpang)*x[0][l];
				x[0][l]=newx[0][l];
				x[j][l]=newx[j][l];
			}
	    	}}
		    ///////////////////////////////////////////		
		    		if(nbframe>=startm) {
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
			 	    		
			 	    		for(i=0;i<h;i++) tmp[i]=0d;
			 	   		for(i=0;i<k;i++)
			 	   		{
			 	   			if(coef[i]!=0)
			 	   			{
			 	   				dtmp=0;
			 	   				tmp2[0]=coef[i];
			 	   				for(j=1;j<h;j++) tmp2[j]=0d;
			 	   				
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
			 	   				
			 	   		
			 	   		if(gmod[nbframe]<5) {
			 	   		//norm
			 	   			
			 	   		set(mon,  coef,  mond1, mond2,  coefd1, coefd2);	
			 	   			
			 	   			
		 	   			for(p=0;p<n;p++) {
					 	   	ftmp=0d;
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
		 	   			
		 	   			if(gmod[nbframe]==0)
		 	   			{
		 	   			//tmpnorm . vecn
			 	   			tmpcor=rnbw(Math.acos(scalprod(tmpnorm,vecn)),1d);
			 	   		
		 	    		
		 	    					alpf=1d-ans;
					 	   			tmpcol=tmpcor[0];
					 	   			tmpcol*=alpf;
					 	    		pixels[currentpix]=(byte)tmpcol;
					 	    		currentpix++;
					 	    		tmpcol=tmpcor[1];
					 	   			tmpcol*=alpf;
					 	    		pixels[currentpix]=(byte)tmpcol;
					 	    		currentpix++;
					 	    		tmpcol=tmpcor[2];
					 	   			tmpcol*=alpf;
					 	    		pixels[currentpix]=(byte)tmpcol;
					 	    		currentpix++;
		 	   			}
		 	   			else {
		 	   				
		 	   				if(gmod[nbframe]==1 ||gmod[nbframe]==3) {
		 	   					uvtmp=uvmap(tmpnorm[0],tmpnorm[1],tmpnorm[2]);
		 	   				}
		 	   				else
		 	   				{
		 	   				tmpvec=vecmir(vecn,tmpnorm);
				 	   			uvtmp=uvmap(tmpvec[0],tmpvec[1],tmpvec[2]);
		 	   				}
		 	   				
		 	   				if(gmod[nbframe]==1 ||gmod[nbframe]==2)
		 	   				{
		 	   				alpf=1d-ans;
			 	   			tmpcol=texture[(int)(res*uvtmp[0])][(int)(res*uvtmp[1])][0];
			 	   			tmpcol*=alpf;
			 	    		pixels[currentpix]=(byte)tmpcol;
			 	    		currentpix++;
			 	    		tmpcol=texture[(int)(res*uvtmp[0])][(int)(res*uvtmp[1])][1];
			 	   			tmpcol*=alpf;
			 	    		pixels[currentpix]=(byte)tmpcol;
			 	    		currentpix++;
			 	    		tmpcol=texture[(int)(res*uvtmp[0])][(int)(res*uvtmp[1])][2];
			 	   			tmpcol*=alpf;
			 	    		pixels[currentpix]=(byte)tmpcol;
			 	    		currentpix++;
		 	   				}
		 	   				else
		 	   				{
		 	   				dtmp=0;
			 	   			dtmp=(int)(16*uvtmp[0])+(int)(16*uvtmp[1]);
			 	   			
			 	   			if(dtmp%2==0)
							{
			 	   				
			 	    		
			 	    				alpf=1d-ans;
					 	   			tmpcol=255;
					 	   			tmpcol*=alpf;
					 	    		pixels[currentpix]=(byte)tmpcol;
					 	    		currentpix++;
					 	    		pixels[currentpix]=(byte)tmpcol;
					 	    		currentpix++;
					 	    		pixels[currentpix]=(byte)tmpcol;
					 	    		currentpix++;
							}
							else
							{
							
					 	   			tmpcol=0;
			
					 	    		pixels[currentpix]=(byte)tmpcol;
					 	    		currentpix++;
					 	    		pixels[currentpix]=(byte)tmpcol;
					 	    		currentpix++;
					 	    		pixels[currentpix]=(byte)tmpcol;
					 	    		currentpix++;
							}
		 	   				}
		 	   		
		 	   			}
		 	   				
			 	   		}
			 	   		else if(gmod[nbframe]==5)
			 	   		{
			 	   		set(mon,  coef,  mond1, mond2,  coefd1, coefd2);
			 	   		ftmp=0d;
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
		 	   			
		 	   			tmpcor=rnbw(ftmp,0.4d);
		 	   		
	 	    		
	 	    		
 	    					alpf=1d-ans;
			 	   			tmpcol=tmpcor[0];
			 	   			tmpcol*=alpf;
			 	    		pixels[currentpix]=(byte)tmpcol;
			 	    		currentpix++;
			 	    		tmpcol=tmpcor[1];
			 	   			tmpcol*=alpf;
			 	    		pixels[currentpix]=(byte)tmpcol;
			 	    		currentpix++;
			 	    		tmpcol=tmpcor[2];
			 	   			tmpcol*=alpf;
			 	    		pixels[currentpix]=(byte)tmpcol;
			 	    		currentpix++;
			 	   		}
			 	   		else if(gmod[nbframe]==6)
			 	   		{
			 	   		alpf=1d-ans;
		 	   			tmpcol=(255*Math.sin(100*(ansv[0])));
		 	   			tmpcol*=alpf;
		 	    		pixels[currentpix]=(byte)tmpcol;
		 	    		currentpix++;
		 	    		tmpcol=(255*Math.sin(77*(ansv[1])));
		 	   			tmpcol*=alpf;
		 	    		pixels[currentpix]=(byte)tmpcol;
		 	    		currentpix++;
		 	    		tmpcol=(255*Math.sin(123*(ansv[2])));
		 	   			tmpcol*=alpf;
		 	    		pixels[currentpix]=(byte)tmpcol;
		 	    		currentpix++;
			 	   		}
			 	   		else if(gmod[nbframe]==7)
			 	   		{
			 	   		dtmp=0;
		 	   			for(i=0;i<n;i++) 
	 					{
	 						 dtmp+=(int)(7*ansv[i]);
	 					}
		 	   			
		 	   		if(dtmp%2==0)
 					{
		 	   		
		 	    		
		 	    			alpf=1d-ans;
			 	   			tmpcol=255;
			 	   			tmpcol*=alpf;
			 	    		pixels[currentpix]=(byte)tmpcol;
			 	    		currentpix++;
			 	    		
			 	    		pixels[currentpix]=(byte)tmpcol;
			 	    		currentpix++;
			 	    		
			 	    		pixels[currentpix]=(byte)tmpcol;
			 	    		currentpix++;
 					}
 					else
 					{
			 	   			tmpcol=0;
			 	    		pixels[currentpix]=(byte)tmpcol;
			 	    		currentpix++;
			 	    		
			 	    		pixels[currentpix]=(byte)tmpcol;
			 	    		currentpix++;
			 	    		
			 	    		pixels[currentpix]=(byte)tmpcol;
			 	    		currentpix++;
 					}
			 	   		}
			 	   		else
			 	   		{
			 	   			
			 	   			
			 	   			tmpmin=2d;
			 	   			for(i=0;i<n;i++)
			 	   			{
			 	   				tmpf=Math.abs((50d*ansv[i])-(double)((int)(50*ansv[i])));
			 	   				tmpf2=1d-tmpf;
			 	   				
			 	   				if(tmpf<tmpmin) tmpmin=tmpf;
			 	   				if(tmpf2<tmpmin) tmpmin=tmpf2;
			 	   			}
			 	   			
			 	   			tmpcor=neon(tmpmin);
			 	   			
			 	   		alpf=1d-ans;
		 	   			tmpcol=tmpcor[0];
		 	   			tmpcol*=alpf;
		 	    		pixels[currentpix]=(byte)tmpcol;
		 	    		currentpix++;
		 	    		tmpcol=tmpcor[1];
		 	   			tmpcol*=alpf;
		 	    		pixels[currentpix]=(byte)tmpcol;
		 	    		currentpix++;
		 	    		tmpcol=tmpcor[2];
		 	   			tmpcol*=alpf;
		 	    		pixels[currentpix]=(byte)tmpcol;
		 	    		currentpix++;
			 	   		}
		 	    		
			 			
		 	    		
		 	    		
			 
			 	    		
			 	    		for(i=0;i<n;i++) vec[i]+=addy[i];
			 	    	}
			 	    	
			 	    	for(i=0;i<n;i++) vec[i]=vectmp[i]+addz[i];
			 	    }
			 	    	
			 	    	ImageIO.write(image, "bmp", new File("C:/rag/img"+String.format("%04d", nbframe)+".bmp/"));
					      System.out.println(nbframe);
					      //System.out.println(System.currentTimeMillis()-start);

			 	    }
			 	    

			
	    	nbframe++;
	    }
	}
	
	public static double vecprod(double[] v1, double[] v2)
	{
		double ret=0;
		for(int i=0;i<n;i++) ret+=v1[i]*v2[i];
		return ret;
	}
	
	public static double[] svec(double[] v, double s)
	{
		double[] ret = new double[n];
		for(int i=0;i<n;i++) ret[i]=v[i]*s;
		return ret;
	}
	
	public static double[] vecmir(double[] v, double[] n)
	{
		return vecsub(v,svec(n,2d*vecprod(v,n)));
	}
	
	public static double[] vecsub(double[] v1,double[] v2)
	{
		double[] ret = new double[n];
		for(int i=0;i<n;i++) ret[i]=v1[i]-v2[i];
		return ret;
	}
	
	public static double veclgt(double[] v)
	{
		return Math.sqrt(vecprod(v,v));
	}
	
	public static double[] vecnorm(double[] v)
	{
		double prod = veclgt(v);
		double[] ret = new double[n];
		for(int i=0;i<n;i++) ret[i]=v[i]/prod;
		return ret;
	}
	
	public static void displayvec(int[] v)
	{
		int i;
		for(i=0;i<n-1;i++) System.out.print(v[i]+",");
		System.out.println(v[i]);
	}
	
	public static void displayvecf(double[] v)
	{
		int i;
		for(i=0;i<n-1;i++) System.out.print(v[i]+",");
		System.out.println(v[i]);
	}
	
	
	
	
	public static void sum(double[] v1, double[] v2)
	{
		for(int i=0;i<h;i++) v1[i]+=v2[i];
	}
	
	public static void smult(double[] v, double a)
	{
		for(int i=0;i<h;i++) v[i]*=a;
	}
	
	public static void mult(double[] v, int d, double a, double b)
	{
		v[d+1]=b*v[d];
		for(int i=d;i>0;i--) 
		{
			v[i]*=a;
			v[i]+=b*v[i-1];
		}
		v[0]=a*v[0];

	}
	
	public static int[] neon(double x)
	{
		int[]ret = new int[3];
		double tmp;
		/*
		if(x>0.1d) tmp=0;
		else
		{
			tmp=1d-10d*x;
		}
		*/
		tmp=gauss(x)/gauss(0);

		if(tmp<0.5d)
		{
			ret[1]=(int)(510d*tmp);
			ret[0]=0;
			ret[2]=0;
		}
		else
		{
			ret[1]=255;
			ret[0]=(int)(510d*tmp-255d);
			ret[2]=(int)(510d*tmp-255d);
		}
		
		return ret;
	}
	
	public static int[] rnbw(double x, double a)
	{
		int[] ret = new int[3];
		double tmp;
		
		if(x<0) x*=-1d;
		x=(x*a)%1;
		tmp=x%(1d/6d);
		
		if(x<1d/6d)
		{
			ret[0]=255;
			ret[1]=(int) (1530d*tmp);
		}
		else if(x<1d/3d)
		{
			ret[1]=255;
			ret[0]=(int) (255d-1530d*tmp);
		}
		else if(x<0.5d)
		{
			ret[1]=255;
			ret[2]=(int) (1530d*tmp);
		}
		else if(x<2d/3d)
		{
			ret[2]=255;
			ret[1]=(int) (255d-1530d*tmp);
		}
		else if(x<5d/6d)
		{
			ret[2]=255;
			ret[0]=(int) (1530d*tmp);
		}
		else
		{
			ret[0]=255;
			ret[2]=(int) (255d-1530d*tmp);
		}
		
		return ret;
	}
	
	public static double gauss(double x)
	{
		double ret;
		double sigma=0.05d;
		
		ret=1d/(sigma*Math.sqrt(2d*Math.PI));
		ret*=Math.exp((-1d/2d)*x*x*(1d/sigma)*(1d/sigma));
		
		
		return ret;
	}
	
	public static double scalprod(double[] v1, double[] v2)
	{
		int i;
		double ret=0;
		for(i=0;i<n;i++) ret+=v1[i]*v2[i];
		return ret;
	}
			
	
	public static double solve(double[] vec)
	{
		double incr=0;
		double tmp = Math.signum(vec[0]);
		int i;
		
		for(i=0;i<max;i++)
		{
			incr+=delta;
			if(Math.signum(eval(vec,incr))!=tmp) return incr;
		}
		
		return 1d;
	}
	
	public static double eval(double[] vec, double x)
	{
		int i;
		double ret=vec[0];
		double newx=x;
		
		for(i=1;i<h-1;i++)
		{
			ret+=vec[i]*newx;
			newx*=x;
		}
		
		ret+=vec[i]*newx;
		
		return ret;
	}
	
	public static void set(int[][] mon, double[] coef, int[][][] mond1, int[][][] mond2, double[][] coefd1, double[][] coefd2)
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
				else coefd1[i][j]=0d;
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
				else coefd2[i][j]=0d;
			}
		}
	}
	
	
	public static double[] uvmap(double dx, double dy, double dz)
	{
		double[] ret = new double[2];
		
		ret[0]=(0.5d+Math.atan2(dz, dx)/(2d*Math.PI));
		ret[1]=(0.5d+Math.asin(dy)/Math.PI);
		
		return ret;
	}
	
	public static void settexture(int[][][] texture)
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

				texture[i][j][0]=(pixel >> 0) & 255;
				texture[i][j][1]=(pixel >> 8) & 255;
				texture[i][j][2]=(pixel >> 16) & 255;
			}
		}
	}
	

}