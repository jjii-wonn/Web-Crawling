package modelFinal;

class CrawDTO {

	   String title;
	   String date;
	   String contents;
	   String url;
	   
	   
	   CrawDTO(String title, String date, String contents, String url){
		   this.title = title;
		   this.date = date;
		   this.contents = contents;
		   this.url = url;
	   }
	   
	  
	   void retriveData(){
		   System.out.println(title);
		   System.out.println(date);
		   System.out.println(contents);
		   System.out.println(url);
	   }																		// for debug purpose
	   
	   
	   public String getTitle() {
	      return title;
	   }
	   
	   public void setTitle(String title) {
	      this.title = title;
	   }
	   
	   public String getContents() {
	      return contents;
	   }
	   
	   public void setContents(String contents) {
	      this.contents = contents;
	   }
	   
	   public String getDate() {
	      return date;
	   }
	   
	   public void setDate(String date) {
	      this.date = date;
	   }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	   
	   
}
