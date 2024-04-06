package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import sun.rmi.server.InactiveGroupException;

class Pair {
    int val;
    int indx;

    public Pair(int val, int indx) {
        this.val = val;
        this.indx = indx;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "val=" + val +
                ", indx=" + indx +
                '}';
    }
}

class Account {
    String name;
    Set<String> emails = new TreeSet<>();

    public Account() {}

    public Account(String name, List<String> emails) {
        this.name = name;
        this.emails.addAll(emails);
    }

    public Account merge(Account account) {
        this.name = account.name;
        this.emails.addAll(account.emails);
        return this;
    }
}


public class Leetcode {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        Map<Integer, Account> parentToAcc = new HashMap<>();
        List<Account> accountList = new ArrayList<>();
        List<List<String>> ans = new ArrayList<>();

        for (List<String> acc : accounts) {
            accountList.add(new Account(acc.get(0), acc.subList(1, acc.size())));
        }

        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                if (!Collections.disjoint(accountList.get(i).emails, accountList.get(j).emails)) {
                    ds.mergeSet(i, j);
                }
            }
        }

        for (int i=0; i<n; i++) {
            int p = ds.getParent(i);
            parentToAcc.put(p, parentToAcc.getOrDefault(p, new Account()).merge(accountList.get(i)));
        }

        for (Account ac : parentToAcc.values()) {
            List<String> acs = new ArrayList<>();
            acs.add(ac.name);
            acs.addAll(ac.emails);
            ans.add(acs);
        }

        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,3};
        int[] freq = {3,2,-3,1};
//        System.out.println(Arrays.toString(new Leetcode().nextGreaterElements(nums)));
//        System.out.println(new Leetcode().mostFrequentIDs());
    }
}
