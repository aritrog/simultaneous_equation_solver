import java.util.*;
class mathzer2{
	public static void main(String[] args) {
		mathzer2 mz=new mathzer2();
		Scanner in=new Scanner(System.in);
		System.out.println("This program is not case sensitive so please use lower case alphabet");
		System.out.println("enter the number of unknown variables followed by n different equations");
		int i,j,t,c,p,sign=-1,sign1=1,n=in.nextInt();
		double si1;
		in.nextLine();
		String s1,s2,s3,sss="",s1s="",s[]=new String[n];
		ArrayList<String> v=new ArrayList<String>();
		for (i=0;i<n;i++ ) {
			s[i]=in.nextLine();
		}
		HashMap<String,Double> var = new HashMap<>();
		double d[][]=new double[n][n+1];
		double g[]=new double [n];
		StringTokenizer st;
		for (i=0;i<n;i++){
			s1s=s[i].replaceAll("[+-=]"," ");
			st=new StringTokenizer(s1s," ");
			while(st.hasMoreTokens()){sss=st.nextToken();if(v.contains(sss))continue; else v.add(sss);}
			if(st.countTokens()==n)break;
		}
		if(v.size()!=n){
			System.out.println("Number of unknown variable mismatch");
			System.exit(0);
		}
		v.add("abc");
		for (i=0;i<n;i++) {
			s[i]=s[i].replace("\\s","");
			if(s[i].charAt(0)!='-'||s[i].charAt(0)!='+')s[i]="+"+s[i]+"+";
			p=0;
			for (j=0; j<s[i].length();j++ ) {
				if(s[i].charAt(j)=='+'){
					if(j!=0){
						s1=s[i].substring(p,j);
						s2=s1.replaceAll("[a-z]","");
						if(s2.isEmpty())si1=1;
						else {
						si1=Double.parseDouble(s2);}
						s3=s1.replaceAll("[0-9]","");
						if(s3.isEmpty())if(var.containsKey("abc"))var.put("abc",var.get("abc")+Double.parseDouble(s2)*sign*sign1);else var.put("abc",Double.parseDouble(s2)*sign*sign1);
						else {
							if(var.containsKey(s3))var.put(s3,var.get(s3)+si1*sign1*sign*-1);else var.put(s3,si1*sign1*sign*-1);
						}
						sign1=1;
						p=j+1;
					}
					else {
						p=j+1;
						sign1=1;
					}
				}
				else if(s[i].charAt(j)=='-'){
					if(j!=0){
						s1=s[i].substring(p,j);
						s2=s1.replaceAll("[a-z]","");
						if(s2.isEmpty())si1=1;
						else {
						si1=Double.parseDouble(s2);}
						s3=s1.replaceAll("[0-9]","");
						if(s3.isEmpty())if(var.containsKey("abc"))var.put("abc",var.get("abc")+Double.parseDouble(s2)*sign*sign1);else var.put("abc",Double.parseDouble(s2)*sign*sign1);
						else {
							if(var.containsKey(s3))var.put(s3,var.get(s3)+si1*sign1*sign*-1);else var.put(s3,si1*sign1*sign*-1);
						}
						sign1=-1;
						p=j+1;
					}
					else {
						p=j+1;
						sign1=-1;
					}
				}
				else if(s[i].charAt(j)=='='){
					if(j!=0){
						s1=s[i].substring(p,j);
						s2=s1.replaceAll("[a-z]","");
						if(s2.isEmpty())si1=1;
						else {
						si1=Double.parseDouble(s2);}
						if(s1.matches("[0-9]"))if(var.containsKey("abc"))var.put("abc",var.get("abc")+Double.parseDouble(s2)*sign*sign1);else var.put("abc",Double.parseDouble(s2)*sign1*sign);
						else {
							s3=s1.replaceAll("[0-9]","");
							if(var.containsKey(s3))var.put(s3,var.get(s3)+si1*sign1*sign*-1);else var.put(s3,si1*sign1*sign*-1);
						}
						sign =1;
						if(s[i].charAt(j+1)!='-'&&s[i].charAt(j+1)!='+'){
							sign1=1;
							p=j+1;
						}
						else{
						if(s[i].charAt(j+1)=='+')sign1=1;else if(s[i].charAt(j+1)=='-')sign1=-1;
						p=j+2;
						j++;}
					}
					else {
						System.out.println("invalid equation");
						System.exit(0);
					}

				}
			}
			for(j=0;j<n;j++)d[i][j]=var.containsKey(v.get(j))?var.get(v.get(j)):0;
			g[i]=var.containsKey("abc")?var.get("abc"):var.get("");	
			var.clear();
			sign=-1;sign1=1;
						
		}
		double tx=d[n-1][n-1];
		int k;
		double clonedet[][]=new double[n][n];
		double dett=mz.det(d,n);
		if(dett==0){System.out.println("This set of equations has infinite number of solutions"); System.exit(0);}
		d[n-1][n-1]=tx;
		for (k=0;k<n ;k++ ) {
			for (i=0; i<n; i++) {
				for (j=0; j<n; j++) {
					if(j==k)clonedet[i][j]=g[i];
					else clonedet[i][j]=d[i][j];
				}
			}
		if(k!=n-1)clonedet[n-1][n-1]=tx;
        System.out.print(v.get(k)+'='+(mz.det(clonedet,n)/dett)+"\t");
		}

	}
	public double det(double a[][],int n){
		double h=0.0;
		for(int i=0;i<n-1;i++){
			for (int j=i+1;j<n;j++) {
				h=a[i][j]/a[i][j-1];
				for(int k=j;k<n;k++){
					a[k][j]-=a[k][j-1]*h;
				}
				
			}
		}
		double f=1.0;
		for(int m=0;m<n;m++)f*=a[m][m];
		return Math.floor(f);
	}
}