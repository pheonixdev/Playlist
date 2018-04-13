package com.pheonixdev;

import java.util.*;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<Album>();

    public static void main(String[] args) {
        Album album = new Album("Stormbringer", "Deep purple");
        album.addSong("The gypsy", 4.46);
        album.addSong("High ball shooter", 4.25);
        album.addSong("Hold on", 4.12);
        album.addSong("Lady double dealer", 3.23);
        album.addSong("You can't do it right", 5.01);
        album.addSong("Soldier of fortune", 4.66);
        albums.add(album);

        album = new Album("For those about to rock", "AC/DC");
        album.addSong("Let's go", 4.23);
        album.addSong("I put the finger on you", 3.25);
        album.addSong("Evil walks", 4.12);
        album.addSong("C.O.D", 4.15);
        album.addSong("Snowballed", 3.56);
        albums.add(album);

        LinkedList<Song> playList = new LinkedList<Song>();
        albums.get(0).addToPlaylist("Hold on", playList);
        albums.get(0).addToPlaylist("Lady double dealer", playList);
        albums.get(0).addToPlaylist("Speed King", playList);  //Does not exist
        albums.get(0).addToPlaylist(5, playList);
        albums.get(1).addToPlaylist(3, playList);
        albums.get(1).addToPlaylist(5, playList);
        albums.get(0).addToPlaylist(16, playList); //Does not exist

        play(playList);
    }

    private static void play(LinkedList<Song> playList){
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playList.listIterator();
        if(playList.isEmpty()){
            System.out.println("No songs in playlist.");
            return;
        }
        else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }

        while(!quit){
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action){
                case 0:
                    System.out.println("Playlist complete.");
                    quit = true;
                    break;
                case 1:
                    if(!forward){
                        if(listIterator.hasNext()){
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if(listIterator.hasNext()){
                        System.out.println("Now playing " +listIterator.next().toString());
                    }
                    else {
                        System.out.println("We have reached the end of the playlist.");
                        forward = false;
                    }
                    break;
                case 2:
                    if(forward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if(listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous().toString());
                    }else {
                        System.out.println("We are at the start of the list");
                        forward = true;
                    }
                    break;
                case 3:
                    if(forward){
                        if(listIterator.hasPrevious()){
                            System.out.println("Now replaying " + listIterator.previous().toString());
                            forward = false;
                        } else{
                            System.out.println("We are at the start of the list.");
                        }
                    } else{
                        if(listIterator.hasNext()){
                            System.out.println("Now replaying " + listIterator.next().toString());
                            forward = true;
                        }
                        else{
                            System.out.println("We are at the end of the list.");
                        }
                    }
                    break;
                case 4:
                    printList(playList);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if(playList.size() >0){
                        listIterator.remove();
                        if(listIterator.hasNext()){
                            System.out.println("Now playing " +listIterator.next());
                        } else if(listIterator.hasPrevious()){
                            System.out.println("Now playing " +listIterator.previous());
                        }
                    }
                    break;
            }
        }
    }

    private static void printMenu(){
        System.out.println("Available actions:\npress");
        System.out.println("0- to quit\n" +
                "1 - to play next song\n" +
                "2 - to play previous song\n" +
                "3 - to replay current song\n" +
                "4 - to list songs in playlist\n" +
                "5 - to print available actions\n"+
                "6 - delete current song.");
    }

    private static void printList(LinkedList<Song> playList){
        Iterator<Song> iterator = playList.iterator();
        System.out.println("=================================");
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("=================================");
    }
}
