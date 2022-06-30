import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class FileIO implements IO {
    ArrayList<Guest> guests = new ArrayList<>();
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();
    public ArrayList<Food> foodOrder = new ArrayList<>();
    ArrayList<Services> orderService = new ArrayList<>();

    public ArrayList<Food> getFoodOrder() {return foodOrder;}

    public void registerGuest() {
        Scanner in = new Scanner(System.in);

        System.out.print("Full name: ");
        String name = in.nextLine();
        System.out.print("Gender (M/F): ");
        String gender = in.next();
        in.nextLine();
        System.out.print("Nationality: ");
        String country = in.nextLine();
        System.out.print("Contact (e-mail or phone): ");
        String contact = in.next();
        Guest guest = new Guest(name, gender, country, contact);
        //System.out.println("New guest registered with ID: " + ID);
        System.out.println("Welcome aboard " + name);
        System.out.println("-------------------------------");
        guests.add(guest);
    }
    public void showAvailableRooms() {
        for (int i = 0; i < rooms.size(); i++) {
            if (!rooms.get(i).isStatus()) {
                System.out.println(rooms.get(i).toString());
            }
        }
    }
    public void bookRoom() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Full Name: ");
        String name = in.nextLine();
        if (findGuest(name) != null) {
            Guest guest = findGuest(name);
            System.out.println("Hello, " + findGuest(name).getName());
            System.out.print("Room number: ");
            int roomNum = in.nextInt();
            if (isAvailable(roomNum)) {
                System.out.print("Insert amount of days: ");
                int days = in.nextInt();
                System.out.print("Insert start date (dd/mm/yyyy): ");
                String start = in.next();
                System.out.print("Insert end date (dd/mm/yyyy): ");
                String end = in.next();
                for (int i = 0; i < rooms.size(); i++) {
                    if (rooms.get(i).getRoomNumber() == roomNum) {
                        rooms.get(i).setStatus(true);
                        Room room = getRoom(roomNum);
                        double balance = 0;
                        bookings.add(new Booking(room, guest, days, start, end));
                        System.out.println("Room booked!");
                        return;
                    }
                }
            } else {
                System.out.println("Room number " + roomNum + " is not available!");
            }
        } else {
            System.out.println("Guest is not registered!");
        }
    }
    public void showAllBookings() {
        if(bookings.size() == 0){
            System.out.println("No Bookings Yet");
        }
        for (int i = 0; i < bookings.size(); i++) {

            System.out.println(bookings.get(i).toString());
        }
    }
    public void showAllRooms() {
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println(rooms.get(i).toString());
        }
    }
    public boolean isAvailable(int roomNum) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomNumber() == roomNum && rooms.get(i).isStatus() == false) {
                return true;
            }
        }
        return false;
    }
    //Checking if the guest has booked a room
    public boolean extendAvailable(int roomNum) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomNumber() == roomNum && rooms.get(i).isStatus() == true) {
                return true;
            }
        }
        return false;
    }
    public void orderFood()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Room number: ");
        int roomNum = in.nextInt();
        for (int i = 0; i < bookings.size(); i++)
        {
            if(bookings.get(i).getRoom().getRoomNumber() == roomNum)
            {
                System.out.print("Enter items to order: ");
                String items = in.next();
                foodOrder.get(i).placeOrder(items);
                bookings.get(i).setFood(new Food(9,foodOrder.get(i).getFoodItem(), foodOrder.get(i).getFoodPrice()));
                System.out.println("The order has been placed!");
                return;
            }
        }
        System.out.println("Sorry, this room is not reserved!");
    }
    //Method to Order Services to a Room
    public void orderService() {
        Scanner in = new Scanner(System.in);
        System.out.print("Room number: ");
        int roomNum = in.nextInt();
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getRoom().getRoomNumber() == roomNum) {
                System.out.print("Enter Services to order: ");
                String items = in.next();
                orderService.get(i).serviceOrder(items);
                bookings.get(i).setService(new Services(1, orderService.get(i).getService(), orderService.get(i).getServicePrice()));
                System.out.println("The order has been placed!");
                return;
            }
        }
        System.out.println("Sorry, this room is not reserved!");
    }

    public void showFoodMenu() {
        for (int i = 0; i < foodOrder.size(); i++) {
            System.out.println(foodOrder.get(i).toString());
        }
    }
    public void showServiceMenu() {
        for (int i = 0; i < orderService.size(); i++) {
            System.out.println(orderService.get(i).toString());
        }
    }

    public void checkout() {
        Scanner in = new Scanner(System.in);
        System.out.print("Room number: ");
        int roomNum = in.nextInt();
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getRoom().getRoomNumber() == roomNum ) {

                    String receipt = bookings.get(i).getReceipt();
                    bookings.remove(i);
                    System.out.println(receipt);
                    for (int j = 0; j < rooms.size(); j++) {
                        if (rooms.get(j).getRoomNumber() == roomNum) {
                            rooms.get(j).setStatus(false);
                            break;
                        }
                    }
                    return;
                }

        }
        System.out.println("Sorry, this room is not reserved!");
    }

    public void cancelBooking() {
        Scanner in = new Scanner(System.in);
        System.out.print("Room number: ");
        int roomNum = in.nextInt();
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getRoom().getRoomNumber() == roomNum) {
                bookings.remove(i);
                for (int j = 0; j < rooms.size(); j++) {
                    if (rooms.get(j).getRoomNumber() == roomNum) {
                        rooms.get(i).setStatus(false);
                        break;
                    }
                }
                System.out.println("Booking is cancelled!");
                return;
            }
        }
        System.out.println("Sorry, this room is not reserved!");
    }
    //Method to Extend booking
    public void extendBooking() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Full Name: ");
        String name = in.nextLine();
        int previous_days;
        String startDate;
        String previousEndDate;
        double balance;
        if (findGuest(name) != null) {
            Guest guest = findGuest(name);
            System.out.print("Room number: ");
            int roomNum = in.nextInt();
            if (extendAvailable(roomNum)) {
                for (int i = 0; i < bookings.size(); i++) {
                    if (bookings.get(i).getRoom().getRoomNumber() == roomNum) {
                        previous_days = bookings.get(i).getDays();
                        startDate = bookings.get(i).getStartDate();
                        previousEndDate = bookings.get(i).getEndDate();
                        System.out.println("Room is booked from " + startDate  +" for " + previous_days + " days till " + previousEndDate);
                        System.out.print("Additional days: ");
                        int days = in.nextInt();
                        System.out.print("Enter the new checkout date (dd/mm/yyyy): ");
                        String end = in.next();
                        Room room = getRoom(roomNum);
                        bookings.remove(i);//deleting old record and updating it with new record by keeping the amount of previous days
                        bookings.add(new Booking(room, guest, days + previous_days, startDate, end));
                        System.out.println("Booking Updated!");
                        return;
                    }
                }
            }
        }
            System.out.println("Sorry, this room is not reserved!");
    }



    public void showAllRegisteredGuests() {
        for (int i = 1; i < guests.size(); i++) {
            System.out.println(guests.get(i).toString());
        }
    }

    public Guest findGuest(String name) {
        for (Guest guest : guests) {
            if (guest.getName().equalsIgnoreCase(name)) {
                return guest;
            }
        }
        return null;
    }

    public Booking findRoom(String room) {
        for (Booking bookedRoom : bookings) {
            if (bookedRoom.getRoom().equals(room)) {
                return bookedRoom;
            }
        }
        return null;
    }

    public Room getRoom(int roomNum) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNum) {
                return room;
            }
        }
        return null;
    }

    @Override
    public void saveData() {

        try {
            PrintWriter pw = new PrintWriter(new File("src/guests.csv"));
            for (Guest guest : guests) {
                pw.write( guest.getName() + "," + guest.getGender() + "," + guest.getCountry() + "," + guest.getContact());
                pw.write("\n");
            }
            pw.close();
            pw = new PrintWriter(new File("src/food.csv"));
            for (Food food : foodOrder) {
                pw.write(food.getFormattedFood());
                pw.write("\n");
            }
            pw.close();
            pw = new PrintWriter(new File("src/services.csv"));
            for (Services service : orderService) {
                pw.write(service.getFormattedService());
                pw.write("\n");
            }
            pw.close();
            pw = new PrintWriter(new File("src/rooms.csv"));
            for (Room room : rooms) {
                pw.write(room.getFormattedRoom());
                pw.write("\n");
            }
            pw.close();
            pw = new PrintWriter(new File("src/bookings.csv"));
            for (Booking booking : bookings) {
                pw.write(booking.getFormattedBooking());
                pw.write("\n");
            }
            pw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void readData() {

        try {
            Scanner in = new Scanner(new File("src/guests.csv"));
            while (in.hasNextLine()) {
                String[] line = in.nextLine().split(",");
                guests.add(new Guest(line[0], line[1], line[2], line[3]));
            }

            in = new Scanner(new File("src/food.csv"));
            while (in.hasNextLine()) {
                String[] line = in.nextLine().split(",");
                foodOrder.add(new Food(Integer.parseInt(line[0]), line[1], Double.parseDouble(line[2])));
            }
            in = new Scanner(new File("src/services.csv"));
            while (in.hasNextLine()) {
                String[] line = in.nextLine().split(",");

                    orderService.add(new Services(Integer.parseInt(line[0]), line[1], Double.parseDouble(line[2])));
            }
            in = new Scanner(new File("src/rooms.csv"));
            while (in.hasNextLine()) {
                String[] line = in.nextLine().split(",");
                String facilities = "";
                for (int i = 5; i < line.length; i++) {
                    facilities += line[i] + " ";
                }
                rooms.add(new Room(Integer.parseInt(line[0]), line[1], line[2], Double.parseDouble(line[3]),
                        Boolean.parseBoolean(line[4]), facilities));
            }
            in = new Scanner(new File("src/bookings.csv"));
            while (in.hasNextLine()) {
                String[] line = in.nextLine().split(",");
                Room room = getRoom(Integer.parseInt(line[0]));
                Guest guest = findGuest(line[1]);
                bookings.add(new Booking(room, guest, Integer.parseInt(line[2]), line[3], line[4]));
            }
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }
    //Method to Delete a registered guest from an array "guests"
    public void deleteGuest() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Guest's Full name: ");
        String name = in.nextLine();
        //Passing the input from user to findGuest method to find the registered guest and then remove them.
        guests.remove(findGuest(name));
        System.out.println("Guest Removed! ");
    }
}


