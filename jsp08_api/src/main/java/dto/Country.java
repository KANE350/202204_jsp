package dto;

public class Country {
	private String sfty_notice_id;
	private String continent_cd;
	private String continent_eng_nm;
	private String continent_nm;
	private String country_iso_alp2;
	private String country_eng_nm;
	private String country_nm;
	private String title;
	private String file_cnt;
	private String file_download_url;
	private String file_path;
	private String html_origin_cn;
	private String txt_origin_cn;
	private String wrt_dt;
	public String getSfty_notice_id() {
		return sfty_notice_id;
	}
	public void setSfty_notice_id(String sfty_notice_id) {
		this.sfty_notice_id = sfty_notice_id;
	}
	public String getContinent_cd() {
		return continent_cd;
	}
	public void setContinent_cd(String continent_cd) {
		this.continent_cd = continent_cd;
	}
	public String getContinent_eng_nm() {
		return continent_eng_nm;
	}
	public void setContinent_eng_nm(String continent_eng_nm) {
		this.continent_eng_nm = continent_eng_nm;
	}
	public String getContinent_nm() {
		return continent_nm;
	}
	public void setContinent_nm(String continent_nm) {
		this.continent_nm = continent_nm;
	}
	public String getCountry_iso_alp2() {
		return country_iso_alp2;
	}
	public void setCountry_iso_alp2(String country_iso_alp2) {
		this.country_iso_alp2 = country_iso_alp2;
	}
	public String getCountry_eng_nm() {
		return country_eng_nm;
	}
	public void setCountry_eng_nm(String country_eng_nm) {
		this.country_eng_nm = country_eng_nm;
	}
	public String getCountry_nm() {
		return country_nm;
	}
	public void setCountry_nm(String country_nm) {
		this.country_nm = country_nm;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFile_cnt() {
		return file_cnt;
	}
	public void setFile_cnt(String file_cnt) {
		this.file_cnt = file_cnt;
	}
	public String getFile_download_url() {
		return file_download_url;
	}
	public void setFile_download_url(String file_download_url) {
		this.file_download_url = file_download_url;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getHtml_origin_cn() {
		return html_origin_cn;
	}
	public void setHtml_origin_cn(String html_origin_cn) {
		this.html_origin_cn = html_origin_cn;
	}
	public String getTxt_origin_cn() {
		return txt_origin_cn;
	}
	public void setTxt_origin_cn(String txt_origin_cn) {
		this.txt_origin_cn = txt_origin_cn;
	}
	public String getWrt_dt() {
		return wrt_dt;
	}
	public void setWrt_dt(String wrt_dt) {
		this.wrt_dt = wrt_dt;
	}
	
	@Override
	public String toString() {
		return "Country [sfty_notice_id=" + sfty_notice_id + ", continent_cd=" + continent_cd + ", continent_eng_nm="
				+ continent_eng_nm + ", continent_nm=" + continent_nm + ", country_iso_alp2=" + country_iso_alp2
				+ ", country_eng_nm=" + country_eng_nm + ", country_nm=" + country_nm + ", title=" + title
				+ ", file_cnt=" + file_cnt + ", file_download_url=" + file_download_url + ", file_path=" + file_path
				+ ", html_origin_cn=" + html_origin_cn + ", txt_origin_cn=" + txt_origin_cn + ", wrt_dt=" + wrt_dt
				+ "]";
	}

	
}
