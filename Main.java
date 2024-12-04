public class Main {
    
    public static int PRINT_MODE = 1;
    public static int SUITES_RUN = 0;
    public static int SUITES_PASSED = 0;
    public static int TESTS_RUN = 0;
    public static int TESTS_PASSED = 0;
    
    public static void main(String[] args) {
        
        System.out.println("!!!!TESTING BST!!!!");
        System.out.println("\nTESTING CONSTRUCTOR"); //------------------------------------------------------------
        BST b0 = new BST();
        assertEquals(b0.toString(), "Empty tree");

        System.out.println("\nTESTING INSERT"); //------------------------------------------------------------
        b0.insert(2);
        b0.insert(1);
        b0.insert(3);
        b0.insert(5);
        System.out.println(b0.toString());
        b0.insert(10);
        b0.insert(7);
        b0.insert(8);
        b0.insert(6);
        System.out.println("\n");
        System.out.println(b0.toString());

        System.out.println("\nTESTING DELETE"); //------------------------------------------------------------
        b0.delete(6);
        b0.delete(10);
        b0.delete(1);
        System.out.println(b0.toString());
        b0.delete(2);
        System.out.println(b0.toString());
        b0.delete(3);
        System.out.println(b0.toString());
        b0.delete(5);
        System.out.println(b0.toString());
        b0.delete(100);
        System.out.println(b0.toString());

        System.out.println("\nTESTING CONTAINS"); //------------------------------------------------------------
        BST b1 = new BST();
        b1.insert(5);
        b1.insert(4);
        b1.insert(6);
        b1.insert(3);
        b1.insert(7);
        b1.insert(2);
        b1.insert(8);
        b1.insert(1);
        b1.insert(9);
        System.out.println(b1.toString());
        assertEquals(b1.contains(2),true);
        assertEquals(b1.contains(5),true);
        assertEquals(b1.contains(9),true);
        assertEquals(b1.contains(10),false);

        System.out.println("\nTESTING GET_HEIGHT"); //------------------------------------------------------------
        assertEquals(b0.getHeight(),2);
        assertEquals(b1.getHeight(),5);
        BST b2 = new BST();
        assertEquals(b2.getHeight(),0);

        System.out.println("\nTESTING PRINT_SEARCH_PATH"); //------------------------------------------------------------

        assertEquals(b1.printSearchPath(9), "5 -> 6 -> 7 -> 8 -> 9");
        BST b3 = new BST();
        b3.insert(50);
        b3.insert(45);
        b3.insert(55);
        b3.insert(40);
        b3.insert(60);
        b3.insert(35);
        b3.insert(65);
        b3.insert(30);
        b3.insert(70);
        b3.insert(25);
        b3.insert(75);
        b3.insert(20);
        b3.insert(80);
        b3.insert(15);
        b3.insert(85);
        b3.insert(10);
        b3.insert(90);
        b3.insert(5);
        System.out.println(b3.toString());
        b3.insert(52);
        b3.insert(57);
        b3.insert(61);
        b3.insert(69);
        b3.insert(74);
        b3.insert(76);
        b3.insert(75);
        b3.insert(81);
        b3.insert(47);
        b3.insert(36);
        b3.insert(32);
        b3.insert(28);
        b3.insert(24);
        b3.insert(19);
        b3.insert(13);
        b3.insert(6);
        b3.insert(7);
        b3.insert(4);
        System.out.println("\n");
        System.out.println(b3.toString());
        assertEquals(b3.printSearchPath(61), "50 -> 55 -> 60 -> 65 -> 61");

        System.out.println("\nTESTING GET_NUM_LEAVES"); //------------------------------------------------------------
        assertEquals(b0.getNumLeaves(), 1);
        assertEquals(b1.getNumLeaves(), 2);
        assertEquals(b3.getNumLeaves(), 17);
        assertEquals(b2.getNumLeaves(), 0);

        System.out.println("\nTESTING extractBiggestSuperficiallyBalancedSubTree"); //---------------------------------
        BST b4 = new BST();
        b4.insert(10);
        b4.insert(5);
        b4.insert(15);
        b4.insert(3);
        b4.insert(7);
        b4.insert(12);
        b4.insert(17);
        System.out.println(b4.toString());
        System.out.println(b4.extractBiggestSuperficiallyBalancedSubTree());
        BST b5 = new BST();
        b5.insert(20);
        b5.insert(10);
        b5.insert(30);
        b5.insert(5);
        b5.insert(15);
        b5.insert(25);
        b5.insert(35);
        b5.insert(24);
        b5.insert(26);
        b5.insert(34);
        b5.insert(36);
        System.out.println("\n F0r b5:");
        System.out.println(b5.toString());
        System.out.println(b5.extractBiggestSuperficiallyBalancedSubTree());


        System.out.println("\nTESTING GET_NODE"); //------------------------------------------------------------------
        System.out.println(b4.getNode(15));
        System.out.println(b4.getNode(3));
        System.out.println(b4.getNode(100));


        System.out.println("\nTESTING MAX AND MIN"); //------------------------------------------------------------------
        System.out.println(b4.findMin());
        System.out.println(b4.findMax());
        System.out.println(b2.findMin());
        System.out.println(b2.findMax());
        System.out.println(b1.findMin());
        System.out.println(b1.findMax());
        System.out.println(b3.findMin());
        System.out.println(b3.findMax());

        System.out.println("\nTESTING IS_SUPERFICIALLY_BALANCED"); //------------------------------------------------------------------
        System.out.println(b0.isSuperficiallyBalanced());
        System.out.println(b1.isSuperficiallyBalanced());
        System.out.println(b2.isSuperficiallyBalanced());
        System.out.println(b3.isSuperficiallyBalanced());
        System.out.println(b4.isSuperficiallyBalanced());

        
    }


    public static <T> void assertEquals(T actual, T expected) {
        switch (PRINT_MODE) {
            case 1:
                TESTS_RUN++;
                if (actual.equals(expected)) {
                    TESTS_PASSED++;
                    System.out.println("Test " + TESTS_RUN + " Passed ");
                } else {
                    System.out.println("Test " + TESTS_RUN + " Failed: Expected " + expected + "\nbut got:\n"
                            + "\n" + actual );
                }
                break;
            case 2:
                System.out.println(actual);
                break;
        }
    }

    public static void assertEquals(String actual, String expected) {
        switch (PRINT_MODE) {
            case 1:
                TESTS_RUN++;
                if (actual.equals(expected)) {
                    TESTS_PASSED++;
                    System.out.println("Test " + TESTS_RUN + " Passed ");
                } else {
                    System.out.println("Test " + TESTS_RUN + " Failed: Expected ");
                    boolean wrong = false;
                    for (int i = 0; i < expected.length(); i++) {
                        if (i < actual.length() && actual.charAt(i) == expected.charAt(i)) {
                            if (wrong) {
                                //System.out.print(ANSI_RESET);
                                wrong = false;
                            }
                        } else if (!wrong) {
                            //System.out.print(ANSI_RED);
                            wrong = true;
                        }
                        System.out.print(expected.charAt(i));
                    }
                    System.out.print("\nbut got\n");
                    wrong = false;
                    for (int i = 0; i < actual.length(); i++) {
                        if (i < expected.length() && actual.charAt(i) == expected.charAt(i)) {
                            if (wrong) {
                                //System.out.print(ANSI_RESET);
                                wrong = false;
                            }
                        } else if (!wrong) {
                            //System.out.print(ANSI_RED);
                            wrong = true;
                        }
                        System.out.print(actual.charAt(i));
                    }
                    //System.err.println(ANSI_RESET);
                }
                break;
            case 2:
                System.out.println(actual);
                break;
        }
    }

}
