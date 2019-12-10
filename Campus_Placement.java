import java.util.*;



class Campus_Placement {



    private static class Students {

        private float CGPA;
        private String Course;
        private int RollNumber;
        private boolean PlacementStatus;    // true implying placed
        private Company Office;
        private Map CompVsMarks;
        private boolean ACC;                // true implies account exists
        private boolean applied;            // false implies hasnt sat for exam

        // Default Constructor
        public Students() {

        }

        // Constructor
        public Students(float CGPA, String Course, int RollNumber, boolean PlacementStatus) {
            
            this.CGPA = CGPA;
            this.Course = Course;
            this.RollNumber = RollNumber;
            this.PlacementStatus = PlacementStatus;
            this.ACC = true;
            this.applied = false;
            Map<Company, Integer> CVM = new HashMap<>();
            this.CompVsMarks = CVM;
        }

        // Query 8 Print info
        public void PrintInfo() {

            System.out.println(this.RollNumber);
            System.out.println(this.CGPA);
            System.out.println(this.Course);

            if (this.PlacementStatus) {
                System.out.println("PLACED at "+this.Office.Comp_Name);
            }
            else {
                System.out.println("NOT PLACED");
            }
        }

        // Place a student
        public void SetPlacementStatus (Company cmpny) {
            this.PlacementStatus = true;
            this.Office = cmpny;
        }

        // Find Placement Status
        public boolean GetPlacementStatus() {
            return this.PlacementStatus;
        }

        // Get CGPA
        public float GetCGPA() {
            return this.CGPA;
        }

        // Get record of marks of a student
        public void GetCompVsMarks() {

            Map<Company, Integer> replace = new HashMap<>();
            replace = this.CompVsMarks;
            for (Map.Entry<Company, Integer> entry : replace.entrySet()) {
                System.out.println(entry.getKey().Comp_Name+" "+entry.getValue());
            }
        }

        // return CompVsMarks
        public Map GiveCompVsMarks() {
            return this.CompVsMarks;
        }

        // Get the branch of the Student
        public String GetCourse() {
            return this.Course;
        }

        // Get the Roll Number of the Student
        public int GetRollNumber() {
            return this.RollNumber;
        }

        // Get account validity
        public boolean GetACC() {
            return this.ACC;
        }

        // Delete account
        public void SetACC() {
            this.ACC = false;
        }

        // whether student sat in a technical round
        public boolean GetApplied() {
            return this.applied;
        }

        // student sat in technical round
        public void SetApplied() {
            this.applied = true;
        }
    }
    


    private static class Company {

        private String Comp_Name;
        private int Eligible_Courses;
        private ArrayList<String> Courses_List;
        private int Req_Students;
        private boolean Applcn_Status;  // true implying Open
        private Map Record;
        private boolean ACC;            // true implying account exists

        // Default Constructor
        public Company() {

        }

        // Constructor
        public Company(String Comp_Name, int Eligible_Courses, int Req_Students, 
                        boolean Applcn_Status, ArrayList<String> Courses_List) {

            this.Comp_Name = Comp_Name;
            this.Eligible_Courses = Eligible_Courses;
            this.Courses_List = Courses_List;
            this.Req_Students = Req_Students;
            this.Applcn_Status = Applcn_Status;
            this.ACC = true;
        }

        // Query 1 and 7 Print info
        public void PrintInfo() {
            
            System.out.println(this.Comp_Name);
            System.out.println("Course Criteria");
            for (int i=0; i<this.Eligible_Courses; i++) {
                System.out.println(this.Courses_List.get(i));
            }

            System.out.println("Number Of Required Students = "+this.Req_Students);
            
            if (this.Applcn_Status) {
                System.out.println("Application Status = OPEN");
            }
            else {
                System.out.println("Application Status = CLOSED");
            }
        }

        // Record roll number vs marks
        public void SetRecord(Map AppStdnts_marks) {
            this.Record = AppStdnts_marks;
        }

        // Get the Placement records for each student for this company
        public Map GetRecord() {
            return this.Record;
        }

        // Change number of vacancies
        public void SetReq_Students(int n) {
            this.Req_Students = n;
        }

        // Get vacancy
        public int GetReq_Students() {
            return this.Req_Students;
        }

        // Change Application Status
        public void SetApplcn_Status() {
            this.Applcn_Status = false;
        }

        // Get whether company is still taking students
        public boolean GetApplcn_Status() {
            return this.Applcn_Status;
        }

        // Get company name
        public String GetComp_Name() {
            return this.Comp_Name;
        }

        // Get account validity
        public boolean GetACC() {
            return this.ACC;
        }

        // Delete Account
        public void SetACC() {
            this.ACC = false;
        }
    }
    


    public static void main(String[] args) {
      
        
        Scanner sc = new Scanner(System.in);

        /* Taking input- (and error handling)
        Number of students
        CGPA and Course

        Creating object Student
        Adding it to arraylist arr_students
        Index of list is the (Roll Number-1) of respective student
        */

        int num_students = sc.nextInt();
        int rollnum = 1;    // count to assign roll number
        ArrayList<Students> arr_students = new ArrayList<Students>();

        int iteration = 0;
        while (iteration < num_students) {
            
            System.out.println("Enter CGPA: ");
            float CGPA = sc.nextFloat();
            if ((CGPA<0) || (CGPA>10)) {
                System.out.println("Enter valid CGPA");
                continue;
            }

            System.out.println("Enter Course: ");
            String Course = sc.next();
            if (! ((Course.equals("CSE")) || (Course.equals("CSAM")) || (Course.equals("ECE")) || (Course.equals("CSD")) || (Course.equals("CSB")) || (Course.equals("CSSS"))) ) {
                System.out.println("Invalid Stream. \nEnter information again.");
                continue;
            }

            else {
                Students student = new Students(CGPA, Course, rollnum, false);
                arr_students.add(student);
                rollnum++;
                iteration++;
           }
        }


        /*
        Taking input- Respective queries
        */

        ArrayList<Company> arr_companies = new ArrayList<Company>();
        Map<String, Company> dict_company = new HashMap<>();

        while (rollnum != 0) {

            int query = sc.nextInt();


            // Add Company
            if (query == 1) {


                /*
                Taking input of respective query
                */
                String company_name = sc.next();
                System.out.println("Number Of Eligible Courses = ");
                int num_courses = sc.nextInt();

                if (num_courses>5) {
                    System.out.println("Number of courses cannot be greater than 5");
                    continue;
                }

                ArrayList<String> course_list = new ArrayList<String>();
                
                for (int i=0; i<num_courses; i++) {
                    course_list.add(sc.next());
                }

                System.out.println("Number Of Required Students = ");
                int num_students_req = sc.nextInt();


                /*
                Creating object Company
                Calling Method to print the information regarding respective query
                Input scores of eligible students for the technical round-
                    Dictionary to store marks of each student
                */
                Company cmpny = new Company(company_name, num_courses, num_students_req, true, course_list);
                arr_companies.add(cmpny);
                dict_company.put(company_name, cmpny);
                cmpny.PrintInfo();

                System.out.println("Enter scores for the technical round");

                Map<Students, Integer> AppStdnts_marks = new HashMap<>();
                
                for (int i=0; i<arr_students.size(); i++) {

                    Map<Company, Integer> CVM = new HashMap<>();
                    for (int j=0; j<course_list.size(); j++){

                        if ((arr_students.get(i).GetCourse().equals(course_list.get(j))) && (!(arr_students.get(i).GetPlacementStatus()))) {

                            System.out.println("Enter score for Roll no. "+arr_students.get(i).GetRollNumber());
                            int score = sc.nextInt();
                            AppStdnts_marks.put(arr_students.get(i), score);
                            arr_students.get(i).GiveCompVsMarks().put(cmpny, score);
                            arr_students.get(i).SetApplied();
                        }
                    }
                    
                }

                cmpny.SetRecord(AppStdnts_marks);
            }



            // Remove the accounts of placed students
            else if (query == 2) {
                
                ArrayList<Integer> removed_list = new ArrayList<Integer>();
                boolean stud_found3 = false;
                System.out.println("Accounts removed for");

                for (int i=0; i<arr_students.size(); i++) {
                    if ((arr_students.get(i).GetPlacementStatus()) && (arr_students.get(i).GetACC())) {
                        removed_list.add(i);
                        System.out.println(arr_students.get(i).GetRollNumber());
                    }
                }

                for (int i=0; i<removed_list.size(); i++) {
                    stud_found3 = true;
                    arr_students.get(i).SetACC();
                }

                if (!(stud_found3)) {
                    System.out.println("None.");
                }
            }



            // Remove the accounts of companies whose applications are closed
            else if (query == 3) {
                
                ArrayList<Integer> removed_list = new ArrayList<Integer>();
                boolean stud_found4 = false;
                System.out.println("Accounts removed for");

                for (int i=0; i<arr_companies.size(); i++) {
                    if ((!(arr_companies.get(i).GetApplcn_Status())) && (arr_companies.get(i).GetACC())) {
                        removed_list.add(i);
                        System.out.println(arr_companies.get(i).GetComp_Name());
                    }
                }

                for (int i=0; i<removed_list.size(); i++) {
                    arr_companies.get(i).SetACC();
                    stud_found4 = true;
                    Company removed_key = dict_company.remove(arr_companies.get(i).GetComp_Name());
                }

                if (!(stud_found4)) {
                    System.out.println("None.");
                }
            }



            // Display number of unplaced students
            else if (query == 4) {
                
                int count_unplaced_students = 0;
                for (int i=0; i<arr_students.size(); i++) {
                    if (!(arr_students.get(i).GetPlacementStatus())) {
                        count_unplaced_students++;
                    }
                }

                System.out.println(count_unplaced_students+" students left");
            }



            // Display names of companies whose applications are open
            else if (query == 5) {
                
                for (int i=0; i<arr_companies.size(); i++) {
                    if (arr_companies.get(i).GetApplcn_Status()) {
                        System.out.println(arr_companies.get(i).GetComp_Name());
                    }
                }
            }



            // Select Students
            else if (query == 6) {
                
                String comp_name = sc.next();
                int vacancy = dict_company.get(comp_name).GetReq_Students();

                if (dict_company.containsKey(comp_name)) {
                    /* Company can select only if it has vacancy and number of students who satisfy the criteria are more than 0
                    
                    dict_company.get(comp_name) returns an object of type Company
                    dict_company.get(comp_name).Record is a HashMap which stores Students as keys and their marks as values
                    */
                    if (dict_company.get(comp_name).GetApplcn_Status()) { 

                        System.out.println("Roll Number of Selected Students");
                        Map<Students, Integer> replace = new HashMap<>();
                        replace = dict_company.get(comp_name).GetRecord();
                        int student_pool = replace.size();

                        while ((vacancy>0) && (student_pool>0)) {

                            Students topper = new Students();
                            int max_marks = -1;

                            for (Map.Entry<Students, Integer> entry : replace.entrySet()) {

                                if (!(entry.getKey().GetPlacementStatus())) {
                                    
                                    int marks = entry.getValue(); 
                                    if (marks > max_marks) {
                                        max_marks = marks;
                                        topper = entry.getKey();
                                    }

                                    else if (marks == max_marks) {
                                        if (topper.GetCGPA() < entry.getKey().GetCGPA()) {
                                            topper = entry.getKey();
                                        }
                                    }
                                }
                            }
                            
                            topper.SetPlacementStatus(dict_company.get(comp_name));
                            System.out.println(topper.GetRollNumber());
                            vacancy--;
                            student_pool--;
                            rollnum--;
                        }

                        dict_company.get(comp_name).SetReq_Students(vacancy);

                        if (vacancy==0) {
                            dict_company.get(comp_name).SetApplcn_Status();
                        }
                    }


                    else {
                        System.out.println("This company cannot select anymore students.");
                    }
                }

                else {
                    System.out.println("No such company with the given name has an account");
                }
            }



            // Display details of the company
            else if (query == 7) {
                
                boolean stud_found0 = false;
                String comp_name = sc.next();
                for (int i=0; i<arr_companies.size(); i++) {
                    if ((arr_companies.get(i).GetComp_Name().equals(comp_name)) && (arr_companies.get(i).GetACC())) {
                        arr_companies.get(i).PrintInfo();
                        stud_found0 = true;
                    }
                }
                
                if (!(stud_found0)) {
                    System.out.println("No company with the given name has an account");
                }
            }



            // Display details of student
            else if (query == 8) {
                
                boolean stud_found1 = false;
                int roll_number = sc.nextInt();
                for (int i=0; i<arr_students.size(); i++) {
                    if ((arr_students.get(i).GetRollNumber() == roll_number) && (arr_students.get(i).GetACC())) {
                        arr_students.get(i).PrintInfo();
                        stud_found1 = true;
                    }
                }

                if (!(stud_found1)) {
                    System.out.println("No student with the given roll number has an account");
                }
            }



            // Display names of companies for which the student has applied
            // and thier scores in technical round of that company
            else if (query == 9) {

                boolean stud_found2 = false;               
                int roll_number = sc.nextInt();

                if (arr_students.get(roll_number-1).GetApplied()){

                    for (int i=0; i<arr_students.size(); i++) {
                        if ((arr_students.get(i).GetRollNumber() == roll_number) && (arr_students.get(i).GetACC())) {
                            
                            arr_students.get(i).GetCompVsMarks();
                            stud_found2 = true;
                            
                        }
                    }

                    if (!(stud_found2)) {
                        System.out.println("No student with the given roll number has an account");
                    }
                }

                else {
                    System.out.println("Has not applied to any company yet.");
                }
            }



            else {
                System.out.println ("Invalid query. \nEnter valid query.");
            }
        }

        sc.close();
        System.exit(0);
    }
}