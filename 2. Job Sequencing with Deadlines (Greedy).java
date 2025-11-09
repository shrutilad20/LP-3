class JobSequencing {
    public static void main(String[] args) {
        int profit[] = {100, 19, 27, 25, 15};
        int deadline[] = {2, 1, 2, 1, 3};
        int n = profit.length, maxD = 3;
        int slot[] = new int[maxD + 1];
        for (int i = 0; i < n; i++)
            for (int j = deadline[i]; j > 0; j--)
                if (slot[j] == 0) {
                  slot[j] = profit[i];
                  break; 
                }
        int sum = 0; for (int p : slot) sum += p;
        System.out.println("Max Profit: " + sum);
    }
}
