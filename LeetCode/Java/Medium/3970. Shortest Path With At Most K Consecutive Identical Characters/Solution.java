class Solution {
    int dik[][];
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        dik=new int[n][k+1];
        List<List<int[]>>lst=new ArrayList<>();
        for(int i=0;i<n;i++)lst.add(new ArrayList<>());
        for(int e[]:edges){
             lst.get(e[0]).add(new int[]{e[1],e[2]});
        }
        for(int u[]:dik)Arrays.fill(u,Integer.MAX_VALUE);
        PriorityQueue<int[]>que=new PriorityQueue<>((a,b)->a[2]-b[2]);
        que.offer(new int[]{0,1,0});
        dik[0][1]=0;
        while(!que.isEmpty()){
            int p[]=que.poll();
            int nd=p[0];
            int con=p[1];
            int w=p[2];
            for(int kk[]:lst.get(nd)){
                if(labels.charAt(nd)==labels.charAt(kk[0]) && con==k)continue;
                if(labels.charAt(nd)==labels.charAt(kk[0])){
                    if(dik[kk[0]][con+1]>w+kk[1]){
                        dik[kk[0]][con+1]=w+kk[1];
                        que.offer(new int[]{kk[0],con+1,dik[kk[0]][con+1]});
                    }
                }else if(dik[kk[0]][1]>w+kk[1]){
                        dik[kk[0]][1]=w+kk[1];
                         que.offer(new int[]{kk[0],1,dik[kk[0]][1]});
                    }
            }
        }
        int min=Integer.MAX_VALUE;
        for(int kk:dik[n-1])if(min>kk)min=kk;
        return min==Integer.MAX_VALUE?-1:min;
    }
}