public class nft {
	protected int nftID;
 	protected String nftName;
 	protected String nftDes;
    protected String imageURL;
    protected String owner;
    protected String listTime;
    protected int price;
    protected int active;
    
    	
    public nft() {
    	
    }
    
    public nft(String nftName)
    {
    	this.nftName = nftName;
    }
    
    public nft(String nftName, String owner)
    {
    	this.nftName = nftName;
    	this.owner = owner;
    }
    
    
	public nft(int nftID, String nftName, String nftDes, String imageURL, String owner, String listTime, int price, int active) {
		this.nftID = nftID;
		this.nftName = nftName;
		this.nftDes = nftDes;
		this.imageURL = imageURL;
		this.owner = owner;
		this.listTime = listTime;
		this.price = price;
		this.active = active;
	}
	
	public nft(String nftName, String nftDes, String imageURL, String owner, String listTime, int price) {
		this.nftName = nftName;
		this.nftDes = nftDes;
		this.imageURL = imageURL;
		this.owner = owner;
		this.listTime = listTime;
		this.price = price;
	}
	
	public nft(String nftDes, String imageURL, String owner, String listTime, int price, int active) {
		this.nftDes = nftDes;
		this.imageURL = imageURL;
		this.owner = owner;
		this.listTime = listTime;
		this.price = price;
		this.active = active;
	}
	
	

	public int getNftID() {
		return nftID;
	}
	public void setNftID(int nftID) {
		this.nftID = nftID;
	}
	public String getNftName() {
		return nftName;
	}
	public void setNftName(String nftName) {
		this.nftName = nftName;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String getNftDes() {
		return nftDes;
	}

	public void setNftDes(String nftDes) {
		this.nftDes = nftDes;
	}

	public String getListTime() {
		return listTime;
	}

	public void setListTime(String listTime) {
		this.listTime = listTime;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
}
