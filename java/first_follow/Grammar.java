import java.util.*;

class Grammar{
    private List<String> Terminals;
    private List<String> NonTerminals;
    private List<Production> productionList;
    private String startSymbol;

    public static String EPS = "9";
    public static String DOLLAR = "$";

    Map<String, Set<String>> mapFirst = new HashMap<>();
    Map<String, Set<String>> mapFollow = new HashMap<>();

    Map<String, Set<String>> prevFollow = new HashMap<>();
    Map<String, Boolean> followCalled = new HashMap<>();

    Grammar(
        List<String> terminals,
        List<String> nonterms,
        List<Production> productionList,
        String startSymbol
    ){
        this.Terminals = terminals;
        this.NonTerminals = nonterms;
        this.productionList = productionList;
        this.startSymbol = startSymbol;
        for(String nt: this.NonTerminals){
            mapFirst.put(nt, new HashSet<>());
            mapFollow.put(nt, new HashSet<>());
        }
        for(String t : this.Terminals){
            mapFirst.put(t, new HashSet<>(Collections.singletonList(t)));
        }
    }

    List<String> getFirstSets(){
        List<String> ans = new ArrayList<>();
        for(String nt : this.NonTerminals){
            ans.add("First(" + nt + ")=" + this.firstSet(nt).toString());
        }
        return ans;
    }

    Set<String> firstSet(String symbol){

        for(Production p : productionList){
            if(p.head.equals(symbol)){
                if(p.body.contains(EPS)) {
                    mapFirst.get(p.head).add(EPS);
                }
                //endregion
                if(p.body.size() > 0) {
                    Set<String> f = firstSet(p.body.get(0));
                    if(null != f)
                        mapFirst.get(p.head).addAll(f);
                }
            }
        }
        return mapFirst.get(symbol);
    }

    List<String> getFollowSets(){
        List<String> ans = new ArrayList<>();
        allFollows(); // Main follow finder function
        for(String nt : this.NonTerminals){
            ans.add("Follow(" + nt + ")=" + mapFollow.get(nt).toString());
        }
        return ans;
    }

    void allFollows(){
        for(String nt : this.NonTerminals){
            prevFollow.put(nt, new HashSet<>( mapFollow.get(nt) ) ) ;
            followCalled.put(nt, false);
        }
        for(Production p : this.productionList){
        }
        while( true ){
            for(String nt : this.NonTerminals){
                Set followNT = followSet(nt);
                mapFollow.get(nt).addAll( followNT );
            }
            if( areMapsEqual(prevFollow, mapFollow)){
                break;
            }
            for(Map.Entry<String, Set<String>> entry : prevFollow.entrySet()){
                String key = entry.getKey();
                entry.setValue( mapFollow.get(key) );
            }
            for(Map.Entry<String, Boolean> entry : followCalled.entrySet()){
                entry.setValue(false);
            }
        }
    }
    boolean areMapsEqual(Map<String, Set<String>> a1, Map<String, Set<String>> a2){
        for(String k1 : a1.keySet()){
            Set s1 = a1.get(k1);
            Set s2 = a2.get(k1);
            if( null == s2  || !s1.equals(s2) ) return false;
        }
        return true;
    }

    Set<String> followSet(String symbol){
        if(symbol.equals(this.startSymbol))
            mapFollow.get(symbol).add(DOLLAR);

        for(Production p : this.productionList){
            if(p.body.contains(symbol)){
                if( p.body.indexOf(symbol) == p.body.size() - 1 ){
                    if(null == followCalled.get(p.head)){
                        throw new RuntimeException("P=" + p + "; NT=" + symbol + " doesn't exist in followCalled");
                    }
                    Set<String> followA = new HashSet<>();
                    if( followCalled.get(p.head) ){
                        followA = prevFollow.get(p.head);
                    }
                    else{
                        followCalled.put(p.head, true);
                        if(! symbol.equals(p.head))
                            followA = followSet(p.head);

                    }
                    mapFollow.get(symbol).addAll(followA);
                }
                else{
                    int nextSymbolIndex = p.body.indexOf(symbol) + 1;
                    String nextSymbol = p.body.get( nextSymbolIndex );
                    Set firstNext = mapFirst.get(nextSymbol);
                    mapFollow.get(symbol).addAll(firstNext);

                    while( firstNext.contains(EPS) && (p.body.size()-1 >= nextSymbolIndex ) ){
                        nextSymbol = p.body.get( nextSymbolIndex );
                        firstNext = mapFirst.get(nextSymbol);
                        mapFollow.get(symbol).addAll(firstNext);
                        nextSymbolIndex++;
                    }

                    if(nextSymbolIndex == p.body.size()){
                       mapFollow.get(symbol).addAll(mapFollow.get(p.head));
                    }
                }
            }
        }
        mapFollow.get(symbol).remove(EPS);
        return mapFollow.get(symbol);
    }
}
