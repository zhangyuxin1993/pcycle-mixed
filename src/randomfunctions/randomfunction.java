package randomfunctions;

import java.util.Random;

public class randomfunction {
	public int uniformRandom(int min_value, int max_value, Random r) {
		int interval = max_value - min_value + 1;
		int x = r.nextInt(interval);
		return min_value + x;
	}

	public double random_uniform_0_1(Random r) {
		return r.nextDouble();
	}

	public double expdev(Random r) {
		double dum;
		dum = this.random_uniform_0_1(r);
		return (-Math.log(dum));
	}
	/*
	 * ��0-bound_number-1֮������number�������
	 */
	   public int[] Num_random(int number,int bound_number){
	    	int[] arr = new int[number]; 
	    	for (int i = 0; i < number; i++){              
				arr[i] = (int) (Math.random()*bound_number) ;   
	    	}
	    	return arr;
	    }
	   /*
	    *  ��0--��bound_number-1��֮������number����ͬ����
	    */
	   public int[] Dif_random(int number,int bound_number){
	    	int[] arr = new int[number];           
			for (int i = 0; i < number; i++){              
				arr[i] = (int) (Math.random()*bound_number) ;              
				for (int j = 0; j < i; j++)              
				{                  
					if (arr[j] == arr[i])                  
					{                      
						i--;                      
						break;                  
						}              
					}          
				}          
	    	return arr;
	    }



}
