import java.util.Scanner;

public class Hotel
{
    FileIO fileIO = new FileIO();

    public void HotelManagement()
    {
        fileIO.readData();
        Scanner in = new Scanner(System.in);
        //String ID;

        while (true)
        {
            System.out.println("Welcome to the booking system");
            System.out.println("-------------------------------");
            System.out.println("1. Register guest");
            System.out.println("2. See all registered guests");
            System.out.println("3. Delete a Registered guest");
            System.out.println("4. Show all rooms");
            System.out.println("5. Show available rooms");
            System.out.println("6. Book a room");
            System.out.println("7. View all bookings");
            System.out.println("8. Show Food menu");
            System.out.println("9. Show Services menu");
            System.out.println("10. Order food to a room");
            System.out.println("11. Get service for a room");
            System.out.println("12. Check out of room");
            System.out.println("13. Cancel reservation");
            System.out.println("14. Extend Stay");
            System.out.println("15. Save and Exit \n");
            System.out.print("Enter your choice: ");
            int choice = in.nextInt();
            switch (choice)
            {
                case 1:

                    fileIO.registerGuest();
                    break;
                case 2:

                    fileIO.showAllRegisteredGuests();
                    break;
                case 3:

                    fileIO.deleteGuest();
                    break;
                case 4:

                    fileIO.showAllRooms();
                    break;
                case 5:

                    fileIO.showAvailableRooms();
                    break;
                case 6:

                    fileIO.bookRoom();
                    break;
                case 7:

                    fileIO.showAllBookings();

                    break;
                case 8:

                    fileIO.showFoodMenu();

                    break;
                case 9:

                    fileIO.showServiceMenu();

                    break;
                case 10:

                    fileIO.showFoodMenu();
                    fileIO.orderFood();
                    break;
                case 11:

                    fileIO.showServiceMenu();
                    fileIO.orderService();
                    break;
                case 12:

                    fileIO.checkout();
                    break;
                case 13:

                    fileIO.cancelBooking();
                    break;
                case 14:

                    fileIO.extendBooking();
                    break;
                case 15:

                    fileIO.saveData();
                    System.out.println("----------------------------------");
                    System.out.println("Thank you for using our booking system");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}
