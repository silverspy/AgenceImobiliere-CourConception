public   class Description {

    private Docs docs;
    public Description(Media str){
        if(str.equals(Media.Web)){
            docs=new DocWeb();
        }
        if(str.equals(Media.Presse)){
            docs=new DocPresse();
        }
    }

    public Docs getDoc(){
        return this.docs;
    }
     public void setDoc(Docs d){
        this.docs=d;
     }

}

