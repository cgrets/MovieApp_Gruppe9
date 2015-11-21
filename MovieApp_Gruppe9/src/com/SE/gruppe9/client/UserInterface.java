package com.SE.gruppe9.client;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.event.dom.client.ClickHandler;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;

public class UserInterface {

	// Arrays to get the data for the Listboxes TODO nicht mehr notwendig
	private final String[] BoxOfficeRevenue = { "Box Office Revenue", "< 100'000", "100'000-1'000'000", "> 1'000'000" };
	private final String[] Runtime = { "Runtime", "≤ 60", "≤ 90", "> 90" };
	private String[] Languages = { "English Language", "French Language", "Thai Language", "German Language" };
	private String[] Countries = { "United Kingdom", "Germany", "Spain" };
	private String[] Genres = { "Action", "Comedy" };

	//all Buttons Tetxtbox and Listboxes
	private final Button goButton = new Button("GO!");
	private final TextBox searchMovieField = new TextBox();
	private final ListBox listBoxYear = new ListBox();
	private final ListBox listBoxOffice = new ListBox();
	private final ListBox listBoxRuntime = new ListBox();
	private final ListBox listBoxLanguage = new ListBox();
	private final ListBox listBoxCountry = new ListBox();
	private final ListBox listBoxGenre = new ListBox();
	private final Button deleteButton = new Button("Delete");

	private Panel hPanel = new Panel();
	private Panel h1Panel = new Panel();
	private Panel vPanel = new Panel();
	private Table table = new Table();
	private String searchedMovie;
	private int count = 0;
	
	public Panel getHPanel(){
		return hPanel;
	}
	
	public Panel getVPanel(){
		return vPanel;
	}

	/**
	 * 
	 */
	private void createAll() {
		hPanel.setLayout(new HorizontalLayout(10));
		vPanel.setLayout(new VerticalLayout(1));
		h1Panel.setLayout(new HorizontalLayout(10));
		// Textbox for movie name
		searchMovieField.setText("Entre a movie name");
		hPanel.add(searchMovieField);

		// go button for movie name
		hPanel.add(goButton);

		// listbox for release year
		listBoxYear.addItem("Year");
		for (int i = 2016; i >= 1888; i--) {
			listBoxYear.addItem(Integer.toString(i));
		}
		listBoxYear.setVisibleItemCount(1);
		hPanel.add(listBoxYear);

		// listbox for box office revenue
		for (int i = 0; i < BoxOfficeRevenue.length; i++) {
			listBoxOffice.addItem(BoxOfficeRevenue[i]);
		}
		listBoxOffice.setVisibleItemCount(1);
		hPanel.add(listBoxOffice);

		// listbox for runtime
		for (int i = 0; i < Runtime.length; i++) {
			listBoxRuntime.addItem(Runtime[i]);
		}
		listBoxRuntime.setVisibleItemCount(1);
		hPanel.add(listBoxRuntime);

		// listbox for language
		listBoxLanguage.addItem("Language");
		//importFilterValue
		for (int i = 0; i < Languages.length; i++) {
			listBoxLanguage.addItem(Languages[i]);
		}
		listBoxLanguage.setVisibleItemCount(1);
		h1Panel.add(listBoxLanguage);

		// listbox for country
		listBoxCountry.addItem("Country");
		for (int i = 0; i < Countries.length; i++) {
			listBoxCountry.addItem(Countries[i]);
		}
		listBoxCountry.setVisibleItemCount(1);
		h1Panel.add(listBoxCountry);

		// listbox for genre
		listBoxGenre.addItem("Genre");
		for (int i = 0; i < Genres.length; i++) {
			listBoxGenre.addItem(Genres[i]);
		}
		listBoxGenre.setVisibleItemCount(1);
		h1Panel.add(listBoxGenre);

		// button to delete filter
		h1Panel.add(deleteButton);
		
		vPanel.add(h1Panel);
//		RootPanel.get("first").add(hPanel);
//		RootPanel.get("second").add(vPanel);
	}

	/**
	 * 
	 */
	public void choseEvents() {
		createAll();
		
		// change event for listbox year
		listBoxYear.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				
				// Get the index of the selected Item
				int itemSelected = listBoxYear.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxYear.getValue(itemSelected);
				if (count < 1) {
					table.createFlexTable();
					table.firstFilter(itemStringSelected, 3);
				} else {
					table.clearFlexTable();
					table.createFlexTable();
					table.secondFilter(itemStringSelected, 1);
				}
				count++;
			}

		});
		
		// change event for listbox box office revenue 
		listBoxOffice.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
			
				// Get the index of the selected Item
				int itemSelected = listBoxOffice.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxOffice.getValue(itemSelected);
				if (count < 1) {
					table.createFlexTable();
					table.firstFilter(itemStringSelected, 4);
				} else {
					table.clearFlexTable();
					table.createFlexTable();
					table.secondFilter(itemStringSelected, 2);
				}
				count++;
			}

		});
		
		// change event for listbox runtime
		listBoxRuntime.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				
				// Get the index of the selected Item
				int itemSelected = listBoxRuntime.getSelectedIndex();
				
				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxRuntime.getValue(itemSelected);
				if (count < 1) {
					table.createFlexTable();
					table.firstFilter(itemStringSelected, 5);
				} else {
					table.clearFlexTable();
					table.createFlexTable();
					table.secondFilter(itemStringSelected, 3);
				}
				count++;
			}
		});
		
		// change event for listbox language
		listBoxLanguage.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				
				// Get the index of the selected Item
				int itemSelected = listBoxLanguage.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxLanguage
						.getValue(itemSelected);
				if (count < 1) {
					Window.alert("Chose first Release Year, Box Time Office or Runtime");
					listBoxLanguage.setSelectedIndex(0);
				} else {
					table.clearFlexTable();
					table.createFlexTable();
					table.secondFilter(itemStringSelected, 4);
					count++;
				}
			}
		});
		
		// change event for listbox country
		listBoxCountry.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				
				// Get the index of the selected Item
				int itemSelected = listBoxCountry.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxCountry.getValue(itemSelected);
				if (count < 1) {
					Window.alert("Chose first Release Year, Box Time Office or Runtime");
					listBoxCountry.setSelectedIndex(0);
				} else {
					table.clearFlexTable();
					table.createFlexTable();
					table.secondFilter(itemStringSelected, 5);
					count++;
				}
			}
		});
		
		// change event for listbox genre

		listBoxGenre.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				
				// Get the index of the selected Item
				int itemSelected = listBoxGenre.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = listBoxGenre.getValue(itemSelected);
				if (count < 1){
					Window.alert("Chose first Release Year, Box Time Office or Runtime");
					listBoxGenre.setSelectedIndex(0);
				} else {
					table.clearFlexTable();
					table.createFlexTable();
					table.secondFilter(itemStringSelected, 6);
					count++;
				}
			}
		});
		deleteButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				count = 0;
				table.clearFlexTable();
				table.clearMap();
				listBoxYear.setSelectedIndex(0);
				listBoxOffice.setSelectedIndex(0);
				listBoxRuntime.setSelectedIndex(0);
				listBoxLanguage.setSelectedIndex(0);
				listBoxCountry.setSelectedIndex(0);
				listBoxGenre.setSelectedIndex(0);
				
			}
		});
	}
	
/*public DataImportServiceAsync filter = GWT.create(DataImportService.class);
	
	public void importFilterValues(String[] keys, int constant) {
		// Initialize the service proxy.
		if (filter == null) {
			filter = GWT.create(DataImportService.class);
		}
		// Set up the callback object.
		final AsyncCallback<String[]> callback = new AsyncCallback<String[]>() {

			public void onFailure(Throwable caught) {
			}

			public void onSuccess(String[] result) {
				for (int i = 0; i < result.length; i++) {
					Languages[i] = result[i].toString();
				}
				for (int i = 0; i < Languages.length; i++) {
					if (!Languages[i].equals("{}")) {
						listBoxLanguage.addItem(Languages[i]);
					}
				}
			}
		};

		filter.filterColumnValues(keys, constant, callback);
	}*/
}