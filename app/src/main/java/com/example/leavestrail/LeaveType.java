package com.example.leavestrail;
public class LeaveType {
    private String name;
    private int totalLeaves;
    private int usedLeaves;

    public LeaveType(String name, int totalLeaves) {
        this.name = name;
        this.totalLeaves = totalLeaves;
        this.usedLeaves = 0;
    }

    public String getName() {
        return name;
    }

    public int getTotalLeaves() {
        return totalLeaves;
    }

    public int getUsedLeaves() {
        return usedLeaves;
    }

    public int getAvailableLeaves() {
        return totalLeaves - usedLeaves;
    }

    public void setUsedLeaves(int usedLeaves) {
        this.usedLeaves = usedLeaves;
    }

    public void reduceLeaveCount() {
        if (usedLeaves < totalLeaves) {
            usedLeaves++;
        }
    }
}
