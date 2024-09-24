package Models;

import Exceptions.MemberExistException;
import Exceptions.MemberNotFoundException;

import java.util.ArrayList;

public class Library {
    private ArrayList<Person> members;
    private ArrayList<Book> books;

    public Library(){
        this.members = new ArrayList<>();
        this.books = new ArrayList<>();
    }

    public ArrayList<Person> getMembers() {
        return members;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addMember(Person newMember) throws MemberExistException {
        // check if there is a member with the same name
        for(int i=0; i < this.members.size(); i++){
            if(this.members.get(i).getName().equals(newMember.getName())){
                throw new MemberExistException("There is an existing member with this name");
            }
        }
        // member doesn't exist. Add the new member to the list of members
        this.members.add(newMember);
    }

    public String removeMember(String name) throws MemberNotFoundException {
        boolean found = false;
        // search for member to remove
        for(int i=0; i < this.members.size(); i++){
            if(this.members.get(i).getName().equals(name)){
                found = true;
                this.members.remove(i);
            }
        }
        if(found)
            return "Member Removed";
        else
            throw new MemberNotFoundException("There is no existing member with the name provided");
    }
}
