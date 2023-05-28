import java.util.Scanner;
public class DishwasherLL {
    public static void printArr(Integer[] arr,int size){
        for(int i=0; i<size; i++){
            if(i == size-1){
                System.out.println(arr[i]);
            }
            else {
                System.out.print(arr[i] + ",  ");
            }
        }
    }
    public static void main(String[] args) {
        /*n = number of invitees
        x = number of courses in meal
        k = ID number of friend pushing disk
        t = Dish has been pushed at dirty stack
        s = course no taken
         */
        int n,x;
        Integer k,t,s;
        /*Taking Inputs*/
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        x = input.nextInt();
        Stack<Integer> washTime = new LStack<>();
        for(int i=0; i<x; i++){
            washTime.push(input.nextInt());
        }
        Stack<Integer>friendId = new LStack<>();
        Stack<Integer>dirtyStack = new LStack<>();
        Stack<Integer>courseNum = new LStack<>();
        for(int i=0; i<(n*x); i++){
            k = input.nextInt();
            t = input.nextInt();
            s = input.nextInt();
            if(k == 0){
                break;
            }
            friendId.push(k);
            dirtyStack.push(t);
            courseNum.push(s);
        }
        /*End time of all dishes have been pushed*/
        /*Time when dishes were kept in dirty Stack*/
        int timeLen = dirtyStack.length();
        int cleanLen = washTime.length();
        int numbLen = courseNum.length();
        /*Bookkeeping Array*/
        Integer[] dirtyTime = new Integer[timeLen];
        Integer[] cleanTime = new Integer[cleanLen];
        Integer[] courseToken = new Integer[numbLen];
        Integer[] endTime = new Integer[timeLen];
        Integer[]  item = new Integer[x];

        for(int i=0; i<x; i++){
            item[i] = i+1;
        }
        for(int i = timeLen - 1; i>=0; i--){
            dirtyTime[i] = dirtyStack.pop();
        }
        for (int i=0; i<timeLen; i++){
            dirtyStack.push(dirtyTime[i]);
        }

        for(int i = cleanLen - 1; i>=0; i--){
            cleanTime[i] = washTime.pop();
        }
        for(int i=0; i<cleanLen; i++){
            washTime.push(cleanTime[i]);
        }

        for(int i = numbLen - 1; i>=0; i--){
            courseToken[i] = courseNum.pop();
        }
        for(int i=0; i<numbLen; i++){
            courseNum.push(courseToken[i]);
        }

        /*Calculating end time and keep it in Stack*/

        /*Implementation of end time in another way*/
        for(int i=0; i<numbLen; i++){
            int idx,idx1 = 0;
            if(i == 0){
                for(int j =0; j<x; j++) {
                    if (courseToken[i].equals(item[j])){
                        endTime[i] = (dirtyTime[i] - 1) + cleanTime[j];
                        break;
                    }
                }
            }
            if(i > 0) {
                if (dirtyTime[i] > endTime[i - 1]) {
                    for(int b = 0; b<x; b++){
                        if(courseToken[i].equals(item[b])){
                            endTime[i] = dirtyTime[i] - 1 + cleanTime[b];
                        }
                    }
                }
                else if(dirtyTime[i].equals(endTime[i-1])){
                    for(int e = 0; e<x; e++){
                        if(courseToken[i].equals(item[e])){
                            endTime[i] = endTime[i-1]+cleanTime[e];
                        }
                    }
                }
                else if(dirtyTime[i] < endTime[i-1]){
                    idx = i;
                    boolean done = false;
                    for(int c = i+1; c<numbLen; c++){
                        if(dirtyTime[c].equals(endTime[idx-1])){
                            idx1 = c;
                            for(int d = 0; d<x; d++){
                                if(courseToken[c].equals(item[d])){
                                    endTime [idx] = endTime[idx-1] + cleanTime[d];
                                    done = true;
                                    break;
                                }
                            }
                            if(done){
                                break;
                            }
                        }
                    }
                    if(idx1 > 0) {
                        for (int tot = 0; tot < x; tot++) {
                            if (courseToken[idx].equals(item[tot])) {
                                endTime[idx1] = endTime[idx] + cleanTime[tot];
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(endTime[numbLen-1]);
        printArr(endTime,numbLen);



        /*Using Array for bookkeeping*/
        Integer[] numCourse = new Integer[n*x];       //for numbering courses using friendID
        Integer[] countCourse = new Integer[n];         //Which course is taken how many times  1->3 2->3 3->2
        for(int i=0; i<n; i++){
            countCourse[i] = 0;
        }
        int len = friendId.length();
        for(int i=len -1; i>=0; i--){
            numCourse[i] = friendId.pop();
            for(int j = n - 1; j>=0; j--){
                if(j + 1 == numCourse[i]){
                    countCourse[j]++;
                    break;
                }
            }
        }
        //printArr(numCourse, len);
        //printArr(countCourse, n);
        /*Refilling Stack*/
        for(int i=0; i<len; i++){
            friendId.push(numCourse[i]);
        }
        /*Checking whether everyone is taking every courses*/
        boolean found = false;
        for(int i=0; i<n; ++i){
            if(countCourse[i] != x){
                found = true;
                break;
            }
        }
        if(found){
            System.out.println("N");
        }
        else if(!found){
            System.out.println("Y");
        }
        /*Printing ID's of friends who have taken full meal*/
        Integer[] fullMeal = new Integer[n];
        int inc = -1;
        for(int i = 0; i<n; ++i){
            if(countCourse[i] == x){
                fullMeal[++inc] = i + 1;
            }
        }
        if(inc > -1){
            for(int pr = 0; pr<=inc; pr++){
                if(pr == inc){
                    System.out.println(fullMeal[pr]);
                }
                else{
                    System.out.print(fullMeal[pr]+", ");
                }
            }
        }

        /*
        3 3
        2 4 1
        1 0 1
        3 1 1
        2 5 1
        1 7 2
        3 8 2
        3 10 3
        1 17 3
        0 0 0
         */




    }
}
