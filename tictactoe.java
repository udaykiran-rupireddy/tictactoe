import java.util.ArrayList;
import java.util.Scanner;

public class tictactoe {
    public static void main(String[]args){
        char[][] board ={{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
        printboard(board);
        Scanner scn= new Scanner(System.in);
        while(check_winner(board)=="C"){

            //human turn;
            int p=1;
            while(true){
                System.out.println("select a number from 1 to 9");
                String userinput = scn.nextLine();
                p=users_correct_input(board,userinput);
                if(p==0){
                    break;
                }
            }
            //checking who won after human turn
            if(check_winner(board)=="X"){
                System.out.println("Hurray! You won");
                break;
            }
            else if(check_winner(board)=="O"){
                System.out.println("The computer has won");
                break;
            }
            else if(check_winner(board)=="T"){
                System.out.println("Its a Tie");
                break;
            }

            //computer turn
            ArrayList<Integer> a=new ArrayList<>();  
            a=AIturn(board) ;
            int u=a.get(0);
            int v=a.get(1);
            board[u][v]='O';
            System.out.println("The computer has chosen "+(u+1)+" row "+(v+1)+" column"); 
            printboard(board);
            //checking who won after computer turn
            if(check_winner(board)=="X"){
                System.out.println("Hurray! You won");
                break;
            }
            else if(check_winner(board)=="O"){
                System.out.println("The computer has won");
                break;
            }
            else if(check_winner(board)=="T"){
                System.out.println("Its a Tie");
                break;
            }

        }    
    }

    public static int users_correct_input(char[][] board,String userinput){
        if(isvalidmove(board,  Integer.parseInt(userinput))){
            System.out.println("You chose the value "+userinput);
            playerturn(board,userinput);  
            printboard(board); 
            return 0;
        }
        else{
            System.out.println("Wrong input! please enter a valid number");
            return 1;
        }
    }

    public static int minimax(char [][]board,boolean is_this_AI_turn){
        String winner_player = check_winner(board);

        if( winner_player!=null && winner_player!="C"){
            return scores(winner_player);
        }

        if (is_this_AI_turn){
            int score=-2;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(board[i][j]==' '){
                        board[i][j]='O';
                        int curr_score=minimax(board,false);
                        board[i][j]=' ';
                        score=Math.max(score,curr_score);
                    }
                }
            }
            return score;
        }
        else{
            int score=2;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(board[i][j]==' '){
                        board[i][j]='X';
                        int curr_score= minimax(board,true);
                        board[i][j]=' ';
                        score=Math.min(score,curr_score);
                    }
                }
            }
            return score;
        }
    }

    public static String check_winner(char [][]board){
        //horizontzl win
        for(int i=0;i<3;i++){
            if(board[i][0]==board[i][1] && board[i][1]==board[i][2]){
                if(board[i][0]=='O') return "O";
                if(board[i][0]=='X' ) return "X";
            }
        }
        //vertical win
        for(int i=0;i<3;i++){
            if(board[0][i]==board[1][i] && board[1][i]==board[2][i]){
                if(board[0][i]=='O') return "O";
                if(board[0][i]=='X') return "X";
            }
        }
        //diagonal 1 win
        if(board[0][0]==board[1][1] && board[1][1]==board[2][2]){
            if(board[0][0]=='X') return "X";
            if(board[0][0]=='O') return "O";
        }
        //diaonal 2 win
        if(board[2][0]==board[1][1] && board[1][1]==board[0][2]){
            if(board[2][0]=='X') return "X";
            if(board[2][0]=='O') return "O";
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]==' ') return "C";//continue further 
            }
        }
        return "T";//game is tie
    }

    private static void playerturn(char[][] board,String userinput){
        switch(userinput){
            case "1":
                board[0][0]='X';
                break;
            case "2":
                board[0][1]='X';
                break;
            case "3":
                board[0][2]='X';
                break;
            case "4":
                board[1][0]='X';
                break;
            case "5":
                board[1][1]='X';
                break;
            case "6":
                board[1][2]='X';
                break;
            case "7":
                board[2][0]='X';
                break;
            case "8":
                board[2][1]='X';
                break;
            case "9":
                board[2][2]='X';
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    /**
     * @param board
     * @return
     */
    public static ArrayList AIturn(char[][] board)
    {
        int score=-2;
        int x=-1;
        int y=-1;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]==' '){
                    board[i][j]='O';
                    int curr_score=minimax(board,false);
                    board[i][j]=' ';
                    if(curr_score>score){
                        score=curr_score;
                        x=i;
                        y=j;
                    }
                }

            }
        }
        ArrayList<Integer> a=new ArrayList<Integer>();
        a.add(x);
        a.add(y);
        return a;
    }

    public static int scores (String a) {
        if(a=="X") return -1;//X is winner (human here)(he is using minimising logic)
        else if(a=="T") return 0;//tie case
        else if(a=="O") return 1;//O is winner (computer here)(it is using maximising logic) 
        return 8;//returned some random number since fnction is showing error
    }
    
    private static boolean isvalidmove(char[][] board,int position){
        switch(position){
            case 1:
                return (board[0][0]==' ');
            case 2:
                return (board[0][1]==' ');
            case 3:
                return (board[0][2]==' ');
            case 4:
                return (board[1][0]==' ');
            case 5:
                return (board[1][1]==' ');
            case 6:
                return (board[1][2]==' ');
            case 7:
                return (board[2][0]==' ');
            case 8:
                return (board[2][1]==' ');
            case 9:
                return (board[2][2]==' ');
            default:
                return false;
        }
    }

    private static void printboard(char[][]board){
        System.out.println(board[0][0]+" | "+board[0][1]+" | "+board[0][2]);
        System.out.println("--+---+--");
        System.out.println(board[1][0]+" | "+board[1][1]+" | "+board[1][2]);
        System.out.println("--+---+--");
        System.out.println(board[2][0]+" | "+board[2][1]+" | "+board[2][2]);
    }

}