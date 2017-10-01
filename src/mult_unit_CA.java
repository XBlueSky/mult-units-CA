import java.awt.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class mult_unit_CA {


    public static void main(String[] args) {

        //declartion
        int bidder_num,bid,type_num;
        String bundle;
        Bidder players[];
        String totalBundle[],temp[];
        ArrayList<Bidder> winner = new ArrayList<Bidder>();

        //input
//        Scanner input = new Scanner(System.in);
//        System.out.print("Please input bidder's number ? ");
//        bidder_num = input.nextInt();
//        System.out.print("Please input types of goods ? ");
//        type_num = input.nextInt();
//        totalBundle = new int[type_num];
//        for(int i=0;i<type_num;i++){
//            totalBundle[i] = input.nextInt();
//        }
//        players = new Bidder[bidder_num];
//
//        for(int i=0;i<bidder_num;i++){
//            players[i] = new Bidder("P" + (i+1));
//            System.out.print("Input P" + (i+1) + "'s bid ? ");
//            bid = input.nextInt();
//            players[i].setbid(bid);
//            System.out.print("Input p" + (i+1) + "'s bundle ? ");
//            bundle = input.next();
//            players[i].setbundle(bundle);
//        }

        type_num = 5;
        totalBundle = new String[type_num];
        totalBundle[0] = "3";
        totalBundle[1] = "2";
        totalBundle[2] = "2";
        totalBundle[3] = "2";
        totalBundle[4] = "2";
        temp = new String[type_num];
        temp[0] = "3";
        temp[1] = "2";
        temp[2] = "2";
        temp[3] = "2";
        temp[4] = "2";
        players = new Bidder[5];
        players[0] = new Bidder("P1");
        players[0].setbid(63);
        players[0].setbundle("1,0,2,1,0");
        players[1] = new Bidder("P2");
        players[1].setbid(90);
        players[1].setbundle("2,1,1,0,0");
        players[2] = new Bidder("P3");
        players[2].setbid(93);
        players[2].setbundle("0,1,0,1,2");
        players[3] = new Bidder("P4");
        players[3].setbid(70);
        players[3].setbundle("0,0,0,2,1");
        players[4] = new Bidder("P5");
        players[4].setbid(50);
        players[4].setbundle("1,0,1,0,0");

//        for(Bidder player : players) {
//            System.out.println(player.getname());
//            for(String perbundle : player.getbundle())
//                System.out.println(perbundle);
//        }


        Bidder.sort(players);

        for(int i=0;i<players.length;i++)
            System.out.println(players[i].getname()+" : "+players[i].getbidPerNum());

        winner.add(Bidder.max(players));
        for(int i=0;i<type_num;i++)
             totalBundle[i] = String.valueOf(Integer.parseInt(totalBundle[i]) - Integer.parseInt(Bidder.max(players).getbundle()[i]));

        for(int i=1;i<players.length;i++){
//            boolean flag = true;
            if(Bidder.disjointSet(players[i].getbundle(),totalBundle)){
                winner.add(players[i]);
                for(int j=0;j<type_num;j++){
                    totalBundle[j] = String.valueOf(Integer.parseInt(totalBundle[j]) - Integer.parseInt(players[i].getbundle()[j]));
                }
            }
        }
//
//        for(int i=0;i<winner.size();i++)
//            System.out.println(winner.get(i).getname());
//        for(int i=0;i<type_num;i++)
//            System.out.println(totalBundle[i]);
//
//
        for(int i=0;i<players.length;i++){
            boolean flag = true;
            for(int j=0; j<winner.size();j++){

                if(players[i].getname().equals(winner.get(j).getname())) {
                    for(int q=0;q<type_num;q++)
                        temp[q] = String.valueOf(Integer.parseInt(temp[q]) - Integer.parseInt(players[i].getbundle()[q]));
                    for (int q = i + 1; q < players.length; q++)
                        if (players[q].getchecked() == -1 && !Bidder.disjointSet(players[q].getbundle(), temp)) {

                            if (flag) {
                                players[i].setcritical(players[q].getbidPerNum() * players[i].gettype());
                                players[q].setchecked(i);
                                //System.out.println(players[q].getname() + " : " + players[i].getname());
                                players[i].set = true;
                                flag = false;
                            } else if(players[q].getchecked() == -1){
                                players[q].setchecked(i);
                            }
                        }
                }
                else{

                    if(!Bidder.disjointSet(players[i].getbundle(),temp) && flag && players[i].set == false){
//                        for(int q=0;q<type_num;q++)
//                            System.out.println(temp[q]);
//                        System.out.println(players[i].getname() + " : " + players[i].getchecked());
                        players[i].setcritical(players[players[i].getchecked()].getbidPerNum()*players[i].gettype());
                        //players[i].setcritical(winner.get(j).getbidPerNum()*players[i].gettype());
                        break;
                    }
                }
            }
        }

        System.out.println("******************* Critical Value *******************");
        for(Bidder player : players)
            System.out.println(player.getname() + " : " + player.getcritical());

        System.out.println("******************* Result *******************");
        for(int i=0;i<winner.size();i++)
            System.out.println(winner.get(i).getname() + " should pay " + winner.get(i).getcritical());
        //System.out.println(player.getbid() + " : " + Arrays.toString(player.getbundle()) + " : " + player.getbidPerNum());


    }


}