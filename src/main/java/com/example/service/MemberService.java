package com.example.service;
import com.example.entity.*;
import java.util.Scanner;
import java.time.LocalDate;

import com.example.repository.Database; //Used temporarily
public class MemberService {


    public void updateFitnessGoals() {
        // Implementation for updating member fitness goals
    }

    public void inputHealthMetrics() {
        // Implementation for inputting health metrics (weight, BMI, etc.)
    }

    public void schedulePrivateSession() {
        // Implementation for scheduling a private session with a trainer
    }

    public void scheduleGroupClass() {
        // Implementation for scheduling a group class
    }

    public void updatePersonalInfo(Member member) {
        // Implementation for updating personal information (address, contact info, etc.)
        Database db = new Database(); //Replace with Repository class function
        Scanner input = new Scanner(System.in);
        System.out.println("--- Update Personal Information ---");
        System.out.println("1: Name\n2: Date of birth\n3: Gender(M/F)\n4: Email\n5: Phone\n0: Cancel");
        System.out.print("Enter option number of field to update: ");
        int opt = -1;
        while(opt == -1){
            try{
                opt = input.nextInt();
                if(opt < 0 || opt > 5) throw new IllegalArgumentException();
            }
            catch(Exception e){
                System.err.println("Invalid option");
                opt = -1;
            }
        }
        switch (opt) {
            case 1:
                System.out.print("Enter new name: ");
                String newName = input.nextLine().trim();
                while(newName.equals("")){
                    System.err.println("New name cannot be empty.");
                    System.out.print("Enter new name: ");
                    newName = input.nextLine().trim();
                }
                member.setName(newName);
                //Replace with Repository class function
                try{
                    db.update(member);
                    System.out.println("New name saved successfully");
                }
                catch(Throwable e){
                    System.err.println("Error saving new name.");
                    e.printStackTrace();
                }
                break;
            case 2:
                LocalDate new_dob = null;
                while(new_dob == null){
                    System.out.print("Enter date of birth (yyyy-mm-dd): ");
                    String new_dob_string = input.nextLine().trim();
                    if(new_dob_string.equals("")){
                        System.out.println("DOB cannot be empty.");
                        continue;
                    }
                    try{
                        new_dob = LocalDate.parse(new_dob_string);
                    }
                    catch(Exception e){
                        System.out.println("Invalid date format");
                    }
                }
                member.setDate_of_birth(new_dob);
                //Replace with Repository class function
                try{
                    db.update(member);
                    System.out.println("Date of birth saved successfully");
                }
                catch(Throwable e){
                    System.err.println("Error saving new name.");
                    e.printStackTrace();
                }
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            case 5:

                break;
        
            case 0:
                break;
        }

    }
}
