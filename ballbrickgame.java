import java.util.*;

class ballbrickgame{
	
	static Scanner sc = new Scanner(System.in);
	static char arr[][];
	static char val[][];
	static int n;
	static int ballcount;
	static int ballcol;
	static char WALL = 'W';
	static char GRND = 'G';
	static char BALL = 'o';
	static char EMTYSPC = ' ';
	
	// Method to Create the Game Box and assigning the Bricks
	static void creategame(){
		
		System.out.println("Enter the NxN of the Matrix : ");
		n = sc.nextInt();
		// sc.nextLine();
		arr = new char[n][n];
		
		
		
		for(int i = 0; i<n; i++){
			arr[i][0] = 'W';
			arr[0][i] = 'W';
		}
		for(int i = n-1; i>=0; i--){
			arr[i][n-1] = 'W';
		}
		for(int i = 1; i<n-1;i++){
			if(i != n/2)
				arr[n-1][i] = 'G';
			else{
				arr[n-1][i] = 'o';
				ballcol = i;
			}
		}
		
		
		char doyouwanttocontinue = 'y';
		
		while(doyouwanttocontinue == 'y'|| doyouwanttocontinue == 'Y'){
			System.out.println("Enter the brick's position and the brick type : ");
			int brickrow = sc.nextInt();
			int brickcol = sc.nextInt();
			char bricknum = sc.next().charAt(0);
			arr[brickrow][brickcol] = bricknum;
			System.out.println("Do you want to Continue(Y or N)? ");
			doyouwanttocontinue = sc.next().charAt(0);
			
		}
		System.out.print("Enter the ball count ");
		ballcount = sc.nextInt();
		sc.nextLine();
		
	}
	
	static void altergame(){
		for(int i = 1; i<n-1; i++){
			if(i != ballcol)
				arr[n-1][i] = GRND;
			else
				arr[n-1][i] = BALL;
		}
	}
	
	static void checkIfCompleted(){
		for(int i = 1; i<n-1; i++){
			for(int j = 1; j<n-1; j++){
				if(arr[i][j] != 0)
					return;
			}
		}
		System.out.println("You Win... Hurray!!");
		System.exit(0);
		
	}
	
	static void demolishsurroundings(int row, int col){
		System.out.println("Calling Surrounding Demolisher");
		arr[row][col] = 0;
		if(arr[row+1][col] != WALL && arr[row+1][col] != GRND && arr[row+1][col] != BALL){// DOWN 
			if(arr[row+1][col] == 'd')
				demolishrow(row+1);
			else if(arr[row+1][col] == 'D')
				demolishsurroundings(row+1,col);
			else
				arr[row+1][col] = 0;
			
		}
		
		if(arr[row-1][col] != WALL && arr[row-1][col] != GRND && arr[row-1][col] != BALL){//UP
			if(arr[row-1][col] == 'd')
				demolishrow(row-1);
			else if(arr[row-1][col] == 'D')
				demolishsurroundings(row-1,col);
			else
				arr[row-1][col] = 0;
		}
		
		if(arr[row+1][col+1] != WALL && arr[row+1][col+1] != GRND && arr[row+1][col+1] != BALL){// RIGHT DIAG DOWN
			if(arr[row+1][col+1] == 'd')
				demolishrow(row+1);
			else if(arr[row+1][col+1] == 'D')
				demolishsurroundings(row+1,col+1);
			else
				arr[row+1][col+1] = 0;
		}
		
		if(arr[row-1][col+1] != WALL && arr[row-1][col+1] != GRND && arr[row-1][col+1] != BALL){// RIGHT DIAG UP
			if(arr[row-1][col+1] == 'd')
				demolishrow(row-1);
			else if(arr[row-1][col+1] == 'D')
				demolishsurroundings(row-1,col+1);
			else
				arr[row-1][col+1] = 0;
		}
		
		if(arr[row][col+1] != WALL && arr[row][col+1] != GRND && arr[row][col+1] != BALL){ // RIGHT
			if(arr[row][col+1] == 'd')
				demolishrow(row);
			else if(arr[row][col+1] == 'D')
				demolishsurroundings(row,col+1);
			else
				arr[row][col+1] = 0;
		}
		
		if(arr[row][col-1] != WALL && arr[row][col-1] != GRND && arr[row][col-1] != BALL){ // LEFT
			if(arr[row][col-1] == 'd')
				demolishrow(row);
			else if(arr[row][col-1] == 'D')
				demolishsurroundings(row,col-1);
			else
				arr[row][col-1] = 0;
		}
		
		if(arr[row-1][col-1] != WALL && arr[row-1][col-1] != GRND && arr[row-1][col-1] != BALL){ // LEFT DIAG UP
			if(arr[row-1][col-1] == 'd'){
				System.out.println("LEft Diagnol");
				demolishrow(row-1);
			}
			else if(arr[row-1][col-1] == 'D')
				demolishsurroundings(row-1,col-1);
			else
				arr[row-1][col-1] = 0;
		}
		
		if(arr[row+1][col-1] != WALL && arr[row+1][col-1] != GRND && arr[row+1][col-1] != BALL){ // LEFT DIAG DOWN
			if(arr[row+1][col-1] == 'd')
				demolishrow(row+1);
			else if(arr[row+1][col-1] == 'D')
				demolishsurroundings(row+1,col-1);
			else
				arr[row+1][col-1] = 0;
		}
		return;
	}
	
	static void demolishrow(int row){
		System.out.println("Calling Row Demolisher");
		for(int dcol = 0; dcol<n; dcol++){
			if(arr[row][dcol] == 'd')
				arr[row][dcol] = 0;
			
			else if(arr[row][dcol] == 'D')
				demolishsurroundings(row,dcol);
			
			else if(arr[row][dcol] != WALL)
				arr[row][dcol] = 0;
		}
		return;
	}
	
	//Character.isDigit(Integer.parseInt(Character.toString(arr[i][ballcol])))
	// Method for Ball's Straight Traversal
	static void straight(){
		// System.out.println("Straight Traversal " );
		for(int i = n-2; i>=1; i--){
			if(arr[i][ballcol] != 0){
				if(arr[i][ballcol] == 'd'){   // This is to Demolish Rows 
					demolishrow(i);
				}
				else if(arr[i][ballcol] == 'D'){ // This is to Demolish Coloumns
					demolishsurroundings(i,ballcol);
				}
				else if(arr[i][ballcol] == '1'){//This is representing the row (i) and the coloumn in which the ball is (ballcol)
					arr[i][ballcol] = 0;
				}
				else 
					arr[i][ballcol]--;
				return;
			}
			
		}
		// altergame();
	}
	
	static void leftHorizontal(int row){
		for(int i = 0; i<n; i++){
			if(i == n-1){
				System.out.println("LEFT Ball hit the Other Side. Ball Count -1");
				ballcount--;
				return;
			}
			if(arr[row][i] != 0 && arr[row][i] != WALL){ // From the first to the end-1 the character is being checked
				if(arr[row][i] == 'd'){   // This is to Demolish Rows 
					demolishrow(row);
				}
				else if(arr[row][i] == 'D'){ // This is to Demolish Coloumns
					demolishsurroundings(row,i);
				}
				else if(arr[row][i] == '1')  // If the Element is 1 change it to nothing
					arr[row][i] = 0;
				else
					arr[row][i]--;           // If the Element is other than that just reduce it by 1
				ballcol = i;
				altergame();
				return;
			}
		}
		
	}
	
	static void rightHorizontal(int row){
		for(int i = n-2; i>=0; i--){
			if(i == 0){
				System.out.println("RIght Ball hit the Other Side. Ball Count -1");
				ballcount--;
				return;
			}
			
			if(arr[row][i] != 0 && arr[row][i] != WALL){
				
				if(arr[row][i] == 'd'){   // This is to Demolish Rows 
					demolishrow(row);
				}
				else if(arr[row][i] == 'D'){ // This is to Demolish Coloumns
					demolishsurroundings(row,i);
				}
				
				else if(arr[row][i] == '1')
					arr[row][i] = 0;
				else
					arr[row][i]--;
				ballcol = i;
				altergame();
				return;
			}
		}
	}
	
	static void leftDiagnol(){
		for(int i = n-1, j = ballcol; i>1;){	
			i--;
			j--;
			if(arr[i][j] == WALL){
				leftHorizontal(i);
				return;
			}
			else if(arr[i][j] == 'd'){
				demolishrow(i);
				ballcol = j;
				altergame();
				return;
			}
			else if(arr[i][j] == 'D'){
				demolishsurroundings(i,j);
				ballcol = j;
				altergame();
				return;
			}
			else if(arr[i][j] == '1'){
				arr[i][j] = 0;
				ballcol = j;
				altergame();
				return;
			}
			
			else if(arr[i][j] != 0){
				arr[i][j]--;
				ballcol = j;
				altergame();
				return;
			}
			
		}
	}
	
	static void rightDiagnol(){
		for(int i = n-1, j = ballcol; i>1;){
			i--;
			j++;
			if(arr[i][j] == WALL){
				rightHorizontal(i);
				return;
			}
			else if(arr[i][j] == 'd'){
				demolishrow(i);
				ballcol = j;
				altergame();
				return;
			}
			else if(arr[i][j] == 'D'){
				demolishsurroundings(i,j);
				ballcol = j;
				altergame();
				return;
			}
			else if(arr[i][j] == '1'){
				arr[i][j] = 0;
				ballcol = j;
				altergame();
				return;
			}
			
			else if(arr[i][j] != 0){
				arr[i][j]--;
				ballcol = j;
				altergame();
				return;
			}
		}
	}
	
	
	
	static void printmatrix(){
		System.out.println();
		for(int i = 0; i<n; i++){
			for(int j = 0; j<n; j++){
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("Ball Count : "+ballcount);
	}
	
	public static void main(String args[]){
		
		creategame();
		System.out.println("The Created Game is shown Below");
		printmatrix();
		while(true){
			System.out.print("Enter the direction in which the ball needs to Traverse ('qt' to quit): ");
			String traversal = sc.nextLine();
			if(traversal.equalsIgnoreCase("st")){
				straight();
				checkIfCompleted();
				printmatrix();
			}
			if(traversal.equalsIgnoreCase("ld")){
				leftDiagnol();
				checkIfCompleted();
				printmatrix();
			}
			if(traversal.equalsIgnoreCase("rd")){
				rightDiagnol();
				checkIfCompleted();
				printmatrix();
			}
			if(traversal.equalsIgnoreCase("qt"))
				break;
			if(ballcount == 0){
				System.out.println("GAME OVER");
				break;
			}
		}
		// System.out.println(ballcol);
		
		
		
	}
}