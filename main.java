import java.util.*;
import java.util.regex.*;


class Main {
    public static Hashtable<String, String> loginDetails = new Hashtable<>();
    public static int getChoice(){
        int input;
        Scanner sc = new Scanner(System.in);
        while (true){
            try{
                System.out.print("원하시는 업무를 선택해 주세요.\n1. 로그인 2. 회원가입 3. 종료\n선택하기 : ");
                input = Integer.parseInt(sc.nextLine());
                System.out.println("********************");
                if (input == 1 || input == 2 || input == 3){
                    break;
                }
                else {
                    System.out.println("\n잘못 입력하였습니다. 1 ~ 3 숫자를 입력해 주세요");
                    System.out.println("********************\n");
                }
            } catch (NumberFormatException nfe){
                System.out.println("********************\n");
                System.out.println("잘못 입력하였습니다. 1 ~ 3 숫자를 입력해 주세요");
                System.out.println("********************\n");
            }
        }
        return input;
    }
    public static boolean validateId(String id){
        String regex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        //check if there are illegal characters
        Matcher matcher = pattern.matcher(id);
        return matcher.find();
    }
    public static boolean validatePwd(String pwd){
        String regex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        //check if there are illegal characters
        Matcher matcher = pattern.matcher(pwd);
        return matcher.find();
    }
    public static void createUser(){

        Scanner sc = new Scanner(System.in);

        String id = "";
        String password = "";
        boolean idCheck = false;
        boolean pwdCheck = false;

        System.out.println("\n회원가입을 진행합니다\n** 주의 ** 아이디와 비밀번호는 알파벳 대소문자와 숫자로만 이뤄져야 합니다.");
        System.out.println("** 주의 ** 아이디는 대소문자를 구분하지 않습니다.\n** 주의 ** 비밀번호는 10자 이상이여야합니다.");

        //id check
        while(!idCheck){
            System.out.print("아이디를 입력하세요 : ");
            id = sc.nextLine();

            //check if id chars are valid
            if (!validateId(id)){System.out.println("특수 문자는 포함할 수 없습니다.\n알파벳 대소문자와 숫자로만 입력하세요.");}
            //check if username exists
            id = id.toLowerCase();
            if (loginDetails.containsKey(id)){System.out.println("이미 존재하는 회원 아이디입니다.\n다시 입력헤주세요.");}
            else if (validateId(id) && !loginDetails.containsKey(id)){idCheck = true;}
        }

        //pwd check
        while (!pwdCheck) {
            System.out.print("비밀번호를 입력하세요 : ");
            password = sc.nextLine();

            if (!validatePwd(password)) {System.out.println("특수 문자는 포함할 수 없습니다.\n알파벳 대소문자와 숫자로만 입력하세요.");}
            if (password.length() < 10) {System.out.println("길이가 너무 짧습니다.\n");}
            else if (validatePwd(password)) {pwdCheck = true;}
        }

        System.out.println(id + "님 회원가입을 축하합니다.");
        loginDetails.put(id,password);
        System.out.println("********************\n");
    }
    public static void admin(){
        Scanner sc = new Scanner(System.in);
        int input;
        System.out.println("admin 계정으로 로그인 되었습니다.");
        while (true){
            try{
                System.out.println("원하시는 업무를 선택해 주세요.\n1. 전체 회원 조회 2. 로그아웃");
                System.out.print("선택하기 : ");
                input = Integer.parseInt(sc.nextLine());
                System.out.println("********************\n");
                if (input == 1){
                    Enumeration<String> e = loginDetails.keys();
                    System.out.println("현제 등록된 모든 회원들의 정보입니다");
                    while (e.hasMoreElements()){
                        String key = e.nextElement();
                        System.out.println("id : " + key + " pw : " + loginDetails.get(key));
                    }
                    System.out.println("전체 회워 수 : "+ loginDetails.size());
                    System.out.println("********************\n");
                }
                else if (input == 2){
                    System.out.println("로그아웃 되었습니다.");
                    System.out.println("********************\n");
                    System.out.println("********************\n");
                    break;
                }
                else {
                    System.out.println("잘못 입력하였습니다. 1 ~ 2 숫자를 입력해 주세요");
                    System.out.println("********************\n");
                }
            } catch (NumberFormatException nfe){
                System.out.println("잘못 입력하였습니다. 1 ~ 2 숫자를 입력해 주세요");
                System.out.println("********************\n");
            }
        }
    }
    public static void login(){
        Scanner sc = new Scanner(System.in);
        String id, password;

        //receive id and password for user
        System.out.print("\n아이디를 입력하세요 : ");
        id = sc.nextLine();
        System.out.print("비밀번호를 입력하세요 : ");
        password= sc.nextLine();

        //non-existing user
        if (!loginDetails.containsKey(id) && id.compareTo("admin") != 0 ){
            System.out.println("존재하지 않는 회원 아이디 입니다.\n회원가입을 진행해 주시길 바랍니다");
            System.out.println("********************\n");
        }

        else {
            //admin login
            if (id.compareTo("admin") == 0 && password.compareTo("admin") == 0){
                System.out.println("********************\n");
                admin();
            }
            //id and password do not match
            else if (loginDetails.get(id).compareTo(password) != 0){
                System.out.println("비밀 번호가 일치하지 않습니다.");
                System.out.println("********************\n");

            //id and password are matching
            } else if (loginDetails.get(id).compareTo(password) == 0) {

                int input;
                System.out.println(id + " 계정으로 로그인 되었습니다.");
                while (true){
                    try{
                        System.out.println("********************\n");
                        System.out.println("원하시는 업무를 선택해 주세요.\n1. 탈퇴하기 2. 로그아웃");
                        System.out.print("선택하기 : ");
                        input = Integer.parseInt(sc.nextLine());
                        System.out.println("********************\n");
                        if (input == 1){
                            loginDetails.remove(id);
                            System.out.println("탈퇴가 완료되었습니다.\n이용해 주셔서 감사합니다.");
                            System.out.println("********************\n");
                            System.out.println("********************\n");
                            break;
                        }
                        else if (input == 2){
                            System.out.println("로그아웃 되었습니다.");
                            System.out.println("********************\n");
                            System.out.println("********************\n");
                            break;
                        }
                        else {
                            System.out.println("\n잘못 입력하였습니다. 1 ~ 2 숫자를 입력해 주세요");
                            System.out.println("********************\n");
                        }
                    } catch (NumberFormatException nfe){
                        System.out.println("********************\n");
                        System.out.println("잘못 입력하였습니다. 1 ~ 2 숫자를 입력해 주세요");
                        System.out.println("********************\n");
                    }
                }
            }
        }
    }

    public static void main (String[] args){
        //get User Input after showing main screen then run relevant procedure
        boolean run = true;
        System.out.println("안녕하세요. 간단한 로그인 프로그램입니다.");

        while (run){
            int input = getChoice();
            switch (input){
                case 1:
                    login();
                    break;
                case 2:
                    createUser();
                    break;
                case 3:
                    run = false;
                    break;
            }
        }
        System.out.println("\n********************\n");
        System.out.println("로그인 프로그램이 종료합니다.");
        System.out.println("********************\n");
    }
}