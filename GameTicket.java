/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameticket_package;
import java.util.*;

/**
 * main() class.
 */
public class GameTicket {

    /**number of games hard coded. */
    static int number_of_games=6;

    /**Array of Games. */
    static Game[] all_games ;

    /**number of fans hard coded. */
    static int number_of_fans=4;

    /**Array of Fans. */
    static Fan [] all_fans ;

    /**Sets array of games hard coded. */
    public static void set_all_games(){
    
        all_games = new Game[number_of_games];
        
        User_Defined_Date tmp_date = new User_Defined_Date();
        tmp_date.year=2020;
        
        tmp_date.month = 2;
        tmp_date.day = 20;
        all_games[0] = new National_Game(100,"Borg Al-Arab Stadium",tmp_date,10,4,"Al-Ahly","Zamalek"); // code, location, date, number_of_seats_per_level, team 1, team 2
        all_games[0].result ="Al-Ahly"; // for bet result
        
        tmp_date.month = 6;
        tmp_date.day = 30;
        all_games[1] = new International_Game(101,"Camp Nou",tmp_date,10,3,"Egypt","Brazil");
        all_games[1].result ="Brazil";
        
        tmp_date.month = 7;
        tmp_date.day = 25;
        all_games[2] = new National_Game(102,"Esma'iliya Stadium",tmp_date,15,6,"Esma'ili","Gouna");
        all_games[2].result ="TIE";
        
        tmp_date.month = 3;
        tmp_date.day = 15;
        all_games[3] = new International_Game(103,"FNB Stadium",tmp_date,8,2,"Holland","France");
        all_games[3].result ="Holland";
        
        tmp_date.month = 8;
        tmp_date.day = 13;
        all_games[4] = new National_Game(104,"Qahira Stadium",tmp_date,10,3,"Tahta","Enppi");
        all_games[4].result ="Enppi";
        
        tmp_date.month = 12;
        tmp_date.day = 15;
        all_games[5] = new International_Game(105,"The Rose Bowl Stadium",tmp_date,12,5,"USA","Russia");
        all_games[5].result ="TIE";
    
    }
    
    /**Displays Data of all games after being set by previous method. */
    public static void display_all_games(){
    
        for(int i = 0; i < number_of_games; i++){
        
            System.out.println("Game #"+(i+1)); // code, location, date, number_of_seats_per_level, team 1, team 2
            display_specefic_game(all_games[i]);
            
            System.out.println("----------------------------------");
            System.out.println("All Seats in Game #"+(i+1));
            for(int j =0; j < all_games[i].number_of_categories;j++){
            
                for(int x =0 ;x < all_games[i].number_of_seats_per_level; x++){
                
                    display_specefic_seat(all_games[i].all_seats[j][x]);
                
                }
                
                
            
            }
            System.out.println("///////////////////////////////////");
            
        }
        
    }
    
    /**Lets users use all of their functionalities as fans as many times as they want. 
     * @throws IndexOutOfBoundsException
     * @throws Invalid_Input_Exception
     * @throws NumberFormatException
     */
    public static void input_all_fans(){
        Scanner input = new Scanner(System.in);
        System.out.println("Input Data for 4 fans:\n\n");
        all_fans = new Fan[number_of_fans];
        User_Defined_Date fan_date = new User_Defined_Date ();
        
        for(int i =0;i < number_of_fans ; i++){
            all_fans[i] = new Fan();
            
            System.out.println("Fan #"+(i+1));
            
            while(true){
                
                
                System.out.print("Enter\n1 -to check seat availability\n2 -to reserve a ticket\n3 -to cancel reservation"
                + "\n4 -to make a bet\n5 -to upgrade seat category\nOtherwise -if you're done\nChoice: ");
                int choice;
                try{
                    choice = Integer.valueOf(input.next());
                
                    if(choice!=1&&choice!=2&&choice!=3&&choice!=4&&choice!=5)
                        break;
                
                }
                catch(NumberFormatException nfe){
                    System.out.println("Sorry, Choice has to be an integer!\nTry Again!\n");
                    continue;
                }
                int game_code;
                try{
                
                    System.out.print("Enter Game Code: ");
                    game_code = Integer.valueOf(input.next());
                    
                }
                catch(NumberFormatException nfe){
                    System.out.println("Sorry, Game Code has to be an integer!\nTry Again!\n");
                    continue;
                }
               
                int k;
                for(k =0;k<number_of_games;k++){
                    if(all_games[k].code==game_code){
                        break;
                    }
                }
                if(k==number_of_games){
                    try {throw new Invalid_Input_Exception("Sorry this game code doesn't exist");}
                    catch (Invalid_Input_Exception iie){System.out.println(iie.getMessage());continue;}
                }
                display_specefic_game(all_games[k]);
                System.out.println();
                
                if(choice==1){ //boolean check_seat_availability(Seat seat)

                    System.out.print("Enter number of seat: ");
                    int seat_number = input.nextInt();
                    System.out.print("Enter category of seat: ");
                    int seat_category = input.nextInt();
                    try{
                        Seat tmp_seat = all_games[k].all_seats[seat_category-1][seat_number-1];
                        if(all_fans[i].check_seat_availability(tmp_seat)){

                            System.out.println("Seat is available");

                        }
                        else{
                            System.out.println("Sorry, Seat is not available");
                        }

                    }
                    catch(IndexOutOfBoundsException iooore){
                        System.out.println("Sorry, Seat number or category out of range!");
                    }

                }

                else if (choice== 2 ) { //void reserve_ticket(Game game, Seat seat)
                    
                    all_fans[i].reserve_ticket(all_games[k]);

                }
                else if (choice== 3 ){ //void cancel_reservation(Ticket ticket, User_Defined_Date date)
                    
                    all_fans[i].cancel_reservation(all_games[k]);

                } 
                else if (choice== 4 ){ // void make_a_bet(Game game, int whowins)
                    
                    all_fans[i].make_a_bet(all_games[k]);

                } 
                else if (choice== 5 ){ //void upgrade_seat_category(Ticket ticket, Seat seat)

                    all_fans[i].upgrade_seat_category(all_games[k]);

                } 
                else{
                    break;
                }
                
                System.out.println("--------------------------");
            }
               
            System.out.println("//////////////////////////////");
        }
    
    }
    
    /**Used to display data of a specific game.
     * @param g is the game that will be displayed.
     */
    public static void display_specefic_game(Game g){
    
        if(g instanceof National_Game){
                
                System.out.println("[National Game]");
                National_Game national_game = (National_Game)g;
                
                System.out.printf("Code: %d\nLocation: %s\nDate:\nDay: %d\tMonth: %d\tYear: %d\n"
                + "Number of Seats per Level: %d\nNumber of Seat categories: %d\nClub 1: %s\t Club 2: %s\n"
                , national_game.code,national_game.location,national_game.date.day,national_game.date.month
                ,national_game.date.year, national_game.number_of_seats_per_level,national_game.number_of_categories
                ,national_game.club1,national_game.club2);
            }
            else{
                System.out.println("[International Game]");
                International_Game international_game = (International_Game)g;
                
                System.out.printf("Code: %d\nLocation: %s\nDate:\nDay: %d\tMonth: %d\tYear: %d\n"
                + "Number of Seats per Level: %d\nNumber of Seat categories: %d\nCountry 1: %s\t Country 2: %s\n"
                ,international_game.code,international_game.location,international_game.date.day,international_game.date.month
                ,international_game.date.year, international_game.number_of_seats_per_level, international_game.number_of_categories
                ,international_game.country1,international_game.country2);
            }
    
    
    }
    
    /**Used to display data of a specific seat.
     * @param s is the seat that will be displayed.
     */
    public static void display_specefic_seat(Seat s){
    
        System.out.println("Seat Number:\t"+s.getNumber()+"\tSeat Category:\t"+s.getCategory()+"\tSeat Price:\t"+s.price);
        
    
    
    }
    
    /**Used to display data of all available seats in a specific game.
     * @param game is the game of which available seats will be displayed.
     */
    public static void display_available_seats_of_specefic_game(Game game){


            System.out.println("Available Seats in Game with Code #"+game.code);

            for(int j =0; j < game.number_of_categories;j++){

                for(int x =0 ;x < game.number_of_seats_per_level; x++){


                    if(game.all_seats[j][x].isAvailable()){

                        display_specefic_seat(game.all_seats[j][x]);

                    }


                }

            System.out.println("///////////////////////////////////");

        }
    }
    
    /**Used to display data of all fans after using their functionalities. */
    public static void display_all_fans(){

        for(int i =0;i<number_of_fans;i++){
            System.out.println("Fan #"+(i+1));
            System.out.println("Ticket(s)");
            
            if(all_fans[i].fan_tickets.isEmpty())
                System.out.println("None");
            
            for(int x =0; x < all_fans[i].fan_tickets.size();x++){
                Ticket tmp_ticket = all_fans[i].fan_tickets.get(x);
                System.out.println("Ticket #"+(x+1));
                System.out.println("Seat:");
                display_specefic_seat(tmp_ticket.getSeat());
                System.out.println("Game:");
                display_specefic_game(tmp_ticket.game);
                System.out.println("-----------------------------");
            }
            
            System.out.println("Bet(s)");
            
            if(all_fans[i].fan_bets.isEmpty())
                System.out.println("None");
            
            for(int bet_counter=0; bet_counter<all_fans[i].fan_bets.size(); bet_counter++){
                Bet tmp_bet = all_fans[i].fan_bets.get(bet_counter);
                if(tmp_bet.win){

                    System.out.println("Congratulations! You won the bet of game with code: "+tmp_bet.game.code
                    +"\nPrediction is "+tmp_bet.prediction_team+"\nResult is "+tmp_bet.actual_result+"\nPrize is "+tmp_bet.prize);

                }
                else{

                    System.out.println("Sorry! You lost the bet of game with code: "+tmp_bet.game.code
                    +"\nPrediction is "+tmp_bet.prediction_team+"\nResult is "+tmp_bet.actual_result);
                }
               
                System.out.println("-----------------------------");

            }

            System.out.println("////////////////////////////");
        }

    }

    /**Starts the program. */
    public static void main(String[] args) {
        
        /**First Games Array is set. */
        set_all_games();
        
        /**Then this game array is displayed for the user. */
        display_all_games();
        
        /**Users are allowed to use their functionalities. */
        input_all_fans();

        /**All Users data is displayed. */
        display_all_fans();
        
    }
}
/**Class Representing parent of 2 types of games. */
abstract class Game {
    
    /**Result of match 1-> Team1, 2-> Team2, 3->Tie. */
    String result;
    
    /**Game specific code that can't be changed. */
    final int code;
    
    /**Stadium name of match. */
    String location;
    
    /**Date of match. */
    User_Defined_Date date;
    
    /**Number of seats per category. */
    int number_of_seats_per_level;
    
    /**Number of seat categories. */
    int number_of_categories;
    
    /**Array of seats in a match. */
    final Seat [][] all_seats;
            
    /**Constructor: Setting all data members of game object
     *Seat Number is set from 1 to number_of_seats_per_level in each category,
     *Seat Category is set from 1 to number_of_categories.
     * @param code Game specific code that can't be changed.
     * @param location Stadium name of match.
     * @param date Date of match.
     * @param number_of_seats_per_level Number of seats per category.
     * @param number_of_categories Number of seat categories.
     */
    Game(int code,String location, User_Defined_Date date, int number_of_seats_per_level, int number_of_categories){
        
        this.code=code;
        this.location=location;
        this.date=date;
        this.number_of_seats_per_level=number_of_seats_per_level;
        this.number_of_categories = number_of_categories;
        all_seats = new Seat[number_of_categories][number_of_seats_per_level]; 
        
       
        for(int i =0 ;i<number_of_categories; i++){
            for(int j=0; j<number_of_seats_per_level;j++){
                all_seats[i][j] = new Seat(j+1,i+1);           
            }
            
        }
     
    }
    
} 
/**Class Representing child of Class Game and a type of Game (National). */
class National_Game extends Game{
    
    /**
     * Name of first national club that's going to play in this national game.
     */
    final String club1;

    /**
     * Name of second national club that's going to play in this national game.
     */
    final String club2;
    
    /**
    * @param club1 refers to first national club.
    * @param club2 refers to second national club.
    */
    National_Game(int code,String location, User_Defined_Date date, int number_of_seats_per_level,int number_of_categories,String club1, String club2){
        super(code, location, date, number_of_seats_per_level,number_of_categories);
        
        this.club1 = club1;
        this.club2 = club2;
    }
    
}
/**Class Representing child of Class Game and a type of Game (International). */
class International_Game extends Game{

    /**
     * Name of first country that's going to play in this international game.
     */
    final String country1;
    /**
     * Name of second country that's going to play in this international game.
     */
    final String country2;
    
    /**
    * @param country1 refers to first country.
    * @param country2 refers to second country.
    */
    International_Game(int code,String location, User_Defined_Date date, int number_of_seats_per_level,int number_of_categories,String country1, String country2){
        super(code, location, date, number_of_seats_per_level, number_of_categories);
        
        this.country1 = country1;
        this.country2 = country2;
    }
    
}

/**Class Representing any reservable class. */
interface Reservable{
    /**Maximum price of anything reservable. */
    public static final int MAX_PRICE = 100;
    /**Changes availability of the reservable object to false. */
    void reserve();
    /**Changes availability of the reservable object to true. */
    void cancel_reservation();

}
/** Class representing Seats and implements interface reservable. */
class Seat implements Reservable{
    
    /**Seat Number. */
    private int number;
    
    /**Seat Price. */
    final float price;
    
    /**Seat Category (1st,2nd,3rd,...). */
    private int category;
    
    /**Boolean for availability of the seat. */
    private boolean available;

    /**@return Seat Number*/
    public int getNumber() {
        return number;
    }
    /** sets seat number with parameter value.
     * @param number.
     * 
     */
    public void setNumber(int number) {
        this.number = number;
    }
    
    /**@return Seat Category*/
    public int getCategory() {
        return category;
    }
    /** sets seat category with parameter value.
     * @param category.
     * 
     */
    public void setCategory(int category) {
        this.category = category;
    }
    /**@return available or not*/

    public boolean isAvailable() {
        return available;
    }
    /** sets seat availability with parameter value.
     * @param available.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
    /**Constructor, sets data members, price is set according to category 1st category -> highest price
     * availability is true by default until seat is reserved.
     * @param number
     * @param category
     */
    Seat(int number , int category){ 
    
        this.price = (MAX_PRICE/(float)category); // assuming category 1 is in the first line and so on
        this.category = category;
        this.number = number;
        available = true;
    }

   @Override
    public void reserve(){
    
        available = false;
    
    }
   
    @Override
    public void cancel_reservation(){
    
        available = true;
    
    }

}
/** Class representing Tickets. */
class Ticket{
    
    /**Ticket has-a game. */
    final Game game;
    /**Ticket has-a seat. */
    private Seat seat;

    /**@return seat */
    public Seat getSeat() {
        return seat;
    }
    /**sets seat. 
     * @param seat
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    
    /**Constructor, initializes data members. 
      * @throws NullPointerException if game or seat isn't instantiated 
      */
    Ticket(Game game, Seat seat){
    
        this.game = game;
        this.seat = seat;
    
    }
    
}
/** Class representing Fans. */
class Fan{
    
    /**A fan has-a list of tickets. */
    ArrayList<Ticket> fan_tickets;
    
    /**A fan has-a list of bets. */
    ArrayList<Bet> fan_bets ; 
    
    /**Constructor, initializing data members. */
    Fan(){
    
        fan_tickets=new ArrayList<>();
        fan_bets = new ArrayList<>();
    }  
    
    /**@return true if seat is available.
     * @param seat seat of which availability is tested.
     * @throws NullPointerException
     */
    boolean check_seat_availability(Seat seat){
    
        if(seat.isAvailable()){
            return true;
        }
        return false;
    }
    
    /**reserves ticket
     * @param game game of which a specific seat is reserved.
     * @throws Invalid_Input_Exception 
     * @throws IndexOutOfBoundsException 
     * @throws NullPointerException
     */
    void reserve_ticket(Game game){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of seat: ");
        int seat_number = input.nextInt();
        System.out.print("Enter category of seat: ");
        int seat_category = input.nextInt();

        try{
            Seat tmp_seat = game.all_seats[seat_category-1][seat_number-1];
            
            if(check_seat_availability(tmp_seat)){
            
            tmp_seat.reserve();
            fan_tickets.add(new Ticket(game,tmp_seat));
            
            System.out.println("Ticket Reserved");
            System.out.println("Price: "+tmp_seat.price);
            }
            else{
                
                throw new Invalid_Input_Exception("Sorry this seat is not available");

            }
        }
        catch(IndexOutOfBoundsException iooore){
            System.out.println("Sorry, Seat number or category out of range!\n");
            GameTicket.display_available_seats_of_specefic_game(game);
        }
        catch(Invalid_Input_Exception iie){
        
            System.out.println(iie.getMessage());
        
        }
        
    
    }
    
    /**Cancels reservation of a ticket
     * @param game game of which a specific seat's reservation is cancelled.
     * @throws Invalid_Input_Exception
     * @throws IndexOutOfBoundsException 
     * @throws NullPointerException
     */
    void cancel_reservation(Game game){
        Scanner input = new Scanner(System.in);
        
        try{
            User_Defined_Date fan_date = new User_Defined_Date();
            System.out.println("Enter Today's Date");
            System.out.print("Day: ");
            fan_date.day = input.nextInt();
            System.out.print("Month: ");
            fan_date.month = input.nextInt();
            fan_date.year = 2020;

            System.out.print("Enter number of seat: ");
            int seat_number = input.nextInt();
            System.out.print("Enter category of seat: ");
            int seat_category = input.nextInt();
            
            Seat tmp_seat = game.all_seats[seat_category-1][seat_number-1];
            Ticket tmp_ticket = new Ticket(game, tmp_seat);
            
            if(check_seat_availability(tmp_ticket)){
                if((tmp_ticket.game.date.day- fan_date.day >= 3 && tmp_ticket.game.date.month>= fan_date.month) || tmp_ticket.game.date.month>= fan_date.month){

                    tmp_ticket.getSeat().cancel_reservation();
                    fan_tickets.remove(return_index_of_ticket(tmp_ticket));
                    tmp_ticket = null;
                    System.out.println("Cancellation Successful!");

                }
                else{
                        
                    throw new Invalid_Input_Exception("Sorry cancellation can only occur before 4 or more days of the game");

                } 
            }
            else{
        
                throw new Invalid_Input_Exception("Sorry cancellation can only occur if you reserved this ticket!");

            }
        }
        catch(IndexOutOfBoundsException iooore){
            System.out.println("Sorry, Seat number or category out of range!");
        }
        catch(Invalid_Input_Exception iie){
                        
            System.out.println(iie.getMessage());
        }
        
    }
    
    /**@return true if a specific ticket is reserved,Overloaded.
     * @param game game of which a specific ticket is tested.
     * @throws NullPointerException
     */
    private boolean check_seat_availability(Ticket ticket){
    
        
        for(int i =0;i<fan_tickets.size();i++){
        
            if(fan_tickets.get(i).getSeat().getNumber() == ticket.getSeat().getNumber() && fan_tickets.get(i).getSeat().getCategory() == ticket.getSeat().getCategory()){
                return true;
                
            }
        
        }
        return false;
    
    }
    
    /**@return index of a specific ticket in the array list of tickets
     * @param ticket ticket of which index is returned.
     * @throws NullPointerException
     */
    private int return_index_of_ticket(Ticket ticket){

        for(int i =0;i<fan_tickets.size();i++){
        
            if(fan_tickets.get(i).getSeat().getNumber() == ticket.getSeat().getNumber() && fan_tickets.get(i).getSeat().getCategory() == ticket.getSeat().getCategory() ){
                return i;
                
            }
        
        }
        return -1;
    }
    
    /**allows fan to make a bet in any game
     * @param game game of which a bet is made.
     * @throws Invalid_Input_Exception 
     * @throws NullPointerException
     */
    void make_a_bet(Game game){
        Scanner input = new Scanner(System.in);
        if(!check_bet_existence(game)){
            System.out.println("Who do you think will win?\n1 -> Team 1, 2 -> Team 2, 3 -> Tie, otherwise -> irrelevant");
            int whowins = input.nextInt();
            try{
                if(whowins==1||whowins==2||whowins==3){
                    fan_bets.add(new Bet (game,whowins)); 
                    System.out.println("Bet Made!");
                }
                else{
                    throw new Invalid_Input_Exception("Sorry!, 1 -> Team 1, 2 -> Team 2, 3 -> Tie, otherwise -> irrelevant");
                }
            }
            catch(Invalid_Input_Exception iie){

                System.out.println(iie.getMessage());
            }
        }
        else{
        
            try{
               
                throw new Invalid_Input_Exception("Sorry!, you can't bet on the same game again!");
               
            }
            catch(Invalid_Input_Exception iie){

                System.out.println(iie.getMessage());
            }
        
        }
    }
    
    private boolean check_bet_existence(Game game){
    
        
        for(int i =0;i<fan_bets.size();i++){
        
            if(fan_bets.get(i).game.code == game.code ){
                return true;
                
            }
        
        }
        return false;
    
    }
    
    /**allows fan to upgrade seat category of any seat they reserved.
     * @param game game of which a specific seat's category is updated.
     * @throws Invalid_Input_Exception 
     * @throws IndexOutOfBoundsException 
     * @throws NullPointerException     */
    void upgrade_seat_category(Game game){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of current seat: ");
        int seat_number_from = input.nextInt();
        System.out.print("Enter category of current seat: ");
        int seat_category_from = input.nextInt();

        System.out.print("\nEnter number of desired seat: ");
        int seat_number_to = input.nextInt();
        System.out.print("Enter category of desired seat: ");
        int seat_category_to = input.nextInt();

        try{
            Seat tmp_seat = game.all_seats[seat_category_to-1][seat_number_to-1];
            Ticket tmp_ticket = new Ticket(game, game.all_seats[seat_category_from-1][seat_number_from-1]);
            if(check_seat_availability(tmp_ticket)){

                if(tmp_seat.getCategory() < tmp_ticket.getSeat().getCategory() && tmp_seat.getCategory() > 0 ){

                        if(tmp_seat.isAvailable()){

                            fan_tickets.get(return_index_of_ticket(tmp_ticket)).setSeat(tmp_seat);
                            tmp_ticket.getSeat().cancel_reservation();
                            tmp_seat.reserve();
                            tmp_ticket.setSeat(tmp_seat);
                            System.out.println("Seat category upgraded successfully!\nPrice is also upgraded to be: " + tmp_ticket.getSeat().price);

                        }
                        else{

                            throw new Invalid_Input_Exception("Sorry, couldn't update seat category!\nSeat Reserved!");

                        }

                }
                else{

                    throw new Invalid_Input_Exception("Sorry, couldn't update seat category!\nNew category has to be higher than old one!\n"
                    +"Your current category is: "+tmp_ticket.getSeat().getCategory()
                            + "\nAnd the category you want to update to is: "+tmp_seat.getCategory());
                }

            }
            else{

                throw new Invalid_Input_Exception("Sorry but you didn't reserve this ticket!");

            }
        }
        catch(IndexOutOfBoundsException iooore){
            System.out.println("Sorry, old or new seat number or category out of range!\n");
            GameTicket.display_available_seats_of_specefic_game(game);

        }
        catch(Invalid_Input_Exception iie){
                        
            System.out.println(iie.getMessage());
        }
        
    
    }
}

/** Class representing Bets. */
class Bet{
    
    /**A bet has-a game. */
    Game game;
    
    /**A bet has-a prediction. */
    int prediction;
    String prediction_team,actual_result;
    
    /**A bet can be won or lost*/
    boolean win=false;
    
    /**A bet has-a reward if won*/
    float prize=0;
    /**Constructor, initializing data members.
     * @param game setting game
     * @param whowins setting prediction of who'll win
     * @throws NullPointerException
     */
    Bet(Game game, int prediction){
    
        this.game = game;
        this.prediction = prediction;  
        
        if(game instanceof National_Game){
            National_Game ng = (National_Game) game;
            actual_result = ng.result;
            if(prediction == 1)
                prediction_team = ng.club1;
            else if (prediction == 2)
                prediction_team = ng.club2;
            else
                prediction_team = "TIE";
           
        }
        else if(game instanceof International_Game){
            International_Game ig = (International_Game) game;
            actual_result = ig.result;
            if(prediction == 1)
                prediction_team = ig.country1;
            else if (prediction == 2)
                prediction_team = ig.country2;
            else
                prediction_team = "TIE";
        }
        if(prediction_team.equals(actual_result)){
            win=true;
            prize = 100.0f;
        }
        
    }

}
/** Class representing Date. */
class User_Defined_Date{
    /**Date has-a day. */
    int day;
    /**Date has-a month. */
    int month;
    /**Date has-a year. */
    int year;

}
/** Class representing User Defined Exception. Inherits from Exception Java Defined Class*/
class Invalid_Input_Exception extends Exception {
    
    /**Constructor, initializing data member.
     * @param msg setting message of exception
     */
    Invalid_Input_Exception(String msg){
        super(msg);
    }
    
}