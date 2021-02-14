package modelFinal;

class SelectDTO{
	
	String NewsTitle;
	String Sliced_Words;
	int Word_cnt;
	
	SelectDTO(String NewsTitle, String Sliced_Words, int Word_cnt){
		this.NewsTitle = NewsTitle;
		this.Sliced_Words = Sliced_Words;
		this.Word_cnt = Word_cnt;
	}

	public String getNewsTitle() {
		return NewsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		NewsTitle = newsTitle;
	}

	public String getSliced_Words() {
		return Sliced_Words;
	}

	public void setSliced_Words(String sliced_Words) {
		Sliced_Words = sliced_Words;
	}

	public int getWord_cnt() {
		return Word_cnt;
	}

	public void setWord_cnt(int word_cnt) {
		Word_cnt = word_cnt;
	}

}
