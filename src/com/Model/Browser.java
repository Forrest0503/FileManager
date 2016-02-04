package com.Model;

import com.View.BrowserMenu;

public class Browser extends User {

    public Browser() {}
	
	public Browser(String name, String passWord, String role) {
		this.setName(name);
		this.setPassWord(passWord);
		this.setRole(role);
	}
	public void downloadFile(String id) {
        DataProcessor.downloadFile(id);
	}
	public void showFileList() {
		
	}
	public void showMenu() {

        new BrowserMenu(this).show();

	}
}