package com.SE.gruppe9.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.Selection;
import com.googlecode.gwt.charts.client.corechart.PieChart;
import com.googlecode.gwt.charts.client.corechart.PieChartOptions;
import com.googlecode.gwt.charts.client.event.ReadyEvent;
import com.googlecode.gwt.charts.client.event.ReadyHandler;

/**
 * draw Piecharts for visualizing the filtered dataset
 */
public class Piechart {

	private List<Double> bor = new ArrayList<Double>();
	private List<Double> runtime = new ArrayList<Double>();
	private int[] numberOfFilmsBOR = new int[3];
	private int[] numberOfFilmsRT = new int[3];

	final static RootLayoutPanel rp = RootLayoutPanel.get();
	private Button exportVisualButton = new Button("export Visuals");
	private Button backButton = new Button("back");
	private HorizontalPanel hPanel = new HorizontalPanel();
	private VerticalPanel vPanel = new VerticalPanel();

	/**
	 * click events for the export and back button
	 */
	public void createExport() {

		hPanel.add(exportVisualButton);
		hPanel.add(backButton);
		vPanel.add(hPanel);

		// add Button to export the visuals
		exportVisualButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("Take a Screenshot!"
						+ "\nMac user: command + shift + 4"
						+ "\nWindows user: print screen"
						+ "\nLinux user: shift + print screen");
			}
		});

		// leave chart
		backButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				rp.setVisible(false);
			}
		});
	}

	/**
	 * initialize Piechart for visualizing number of movies per Range of Box
	 * Office Revenue (BOR)
	 */
	void createChartBOR() {

		createExport();

		// Create a Dock Panel
		final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);

		// Add text all around
		dockLayoutPanel.addNorth(new HTML(""), 7);
		dockLayoutPanel.addWest(new HTML(""), 14);
		dockLayoutPanel.addSouth(new HTML(""), 5);

		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				PieChart col = new PieChart();
				vPanel.add(col);
				Piechart piechart = new Piechart();
				piechart.drawChartBOR(col);
				dockLayoutPanel.add(vPanel);
			}
		});
		rp.add(dockLayoutPanel);
	}

	/**
	 * draw Piechart for Box Office Revenue
	 * 
	 * @param chart
	 */
	void drawChartBOR(final PieChart chart) {

		// set the number of films per Range of Box Office Revenue
		setNumberOfFilmsBOR();

		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Box Office Revenue");
		dataTable.addColumn(ColumnType.NUMBER, "Number of Films");
		dataTable.addRows(3);
		dataTable.setValue(0, 0, "≤ 100'000");
		dataTable.setValue(0, 1, numberOfFilmsBOR[0]);
		dataTable.setValue(1, 0, "100'000 - 1'000'000");
		dataTable.setValue(1, 1, numberOfFilmsBOR[1]);
		dataTable.setValue(2, 0, "≥ 1'000'000");
		dataTable.setValue(2, 1, numberOfFilmsBOR[2]);

		// Set options
		PieChartOptions options = PieChartOptions.create();
		options.setBackgroundColor("#f0f0f0");

		// options.setColors(colors);
		options.setFontName("Tahoma");
		options.setIs3D(false);
		options.setTitle("Number of Films per Range of Box Office Revenue");
		options.setHeight(450);
		options.setWidth(835);

		// Draw the chart
		chart.draw(dataTable, options);
		chart.addReadyHandler(new ReadyHandler() {

			@Override
			public void onReady(ReadyEvent event) {
				final JsArray<Selection> selectionArray = Selection
						.createArray().cast();
				selectionArray.setLength(1);

				final Selection selection = Selection.create(1, null).cast();
				selectionArray.set(0, selection);

			}
		});

	}

	/**
	 * initialize Piechart for visualizing number of movies per Range of Runtime
	 * (RT)
	 */
	void createChartRT() {

		createExport();
		// Create a Dock Panel
		final DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.EM);

		// Add text all around
		dockLayoutPanel.addNorth(new HTML(""), 7);
		dockLayoutPanel.addWest(new HTML(""), 14);
		dockLayoutPanel.addSouth(new HTML(""), 5);

		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				PieChart col = new PieChart();
				vPanel.add(col);
				Piechart piechart = new Piechart();
				piechart.drawChartRT(col);
				dockLayoutPanel.add(vPanel);
			}
		});
		rp.add(dockLayoutPanel);
	}

	/**
	 * draw Piechart for Runtime
	 * 
	 * @param chart
	 */
	void drawChartRT(final PieChart chart) {

		// set the number of films per Range of Box Office Revenue
		setNumberOfFilmsRT();

		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Runtime");
		dataTable.addColumn(ColumnType.NUMBER, "Number of Films");
		dataTable.addRows(3);
		dataTable.setValue(0, 0, "≤ 60 min");
		dataTable.setValue(0, 1, numberOfFilmsRT[0]);
		dataTable.setValue(1, 0, "60 - 90 min");
		dataTable.setValue(1, 1, numberOfFilmsRT[1]);
		dataTable.setValue(2, 0, "≥ 90 min");
		dataTable.setValue(2, 1, numberOfFilmsRT[2]);

		// Set options
		PieChartOptions options = PieChartOptions.create();
		options.setBackgroundColor("#f0f0f0");

		// options.setColors(colors);
		options.setFontName("Tahoma");
		options.setIs3D(false);
		options.setHeight(450);
		options.setWidth(835);
		options.setTitle("Number of Films per Range of Runtime");

		// Draw the chart
		chart.draw(dataTable, options);
		chart.addReadyHandler(new ReadyHandler() {

			@Override
			public void onReady(ReadyEvent event) {
				final JsArray<Selection> selectionArray = Selection
						.createArray().cast();
				selectionArray.setLength(1);

				final Selection selection = Selection.create(1, null).cast();
				selectionArray.set(0, selection);

			}
		});

	}

	/**
	 * set all box office revenue entries from hashmap to array
	 */
	public void setBOR() {
		for (Map.Entry<String, String> entry : Table.getResultMap().entrySet()) {
			String[] tmp = entry.getValue().split("==");
			if (tmp[2].isEmpty() == false) {
				bor.add(Double.parseDouble(tmp[2]));
			}
		}
	}

	/**
	 * set all runtime entries from hashmap to array
	 */
	public void setRuntime() {
		for (Map.Entry<String, String> entry : Table.getResultMap().entrySet()) {
			String[] tmp = entry.getValue().split("==");
			if (tmp[3].isEmpty() == false) {
				runtime.add(Double.parseDouble(tmp[3]));
			}
		}
	}

	/**
	 * calculate all movies with the same box office revenue range
	 */
	public void setNumberOfFilmsBOR() {
		setBOR();
		int counter1 = 0;
		int counter2 = 0;
		int counter3 = 0;

		for (int i = 0; i < bor.size(); i++) {
			if (bor.get(i) != null) {
				if (bor.get(i) <= 100000) {
					counter1++;
				} else if (bor.get(i) > 100000 && bor.get(i) < 1000000) {
					counter2++;
				} else if (bor.get(i) >= 1000000) {
					counter3++;
				}
			}
		}
		numberOfFilmsBOR[0] = counter1;
		numberOfFilmsBOR[1] = counter2;
		numberOfFilmsBOR[2] = counter3;
	}

	/**
	 * calculate all movies with the same runtime range
	 */
	public void setNumberOfFilmsRT() {
		setRuntime();
		int counter1 = 0;
		int counter2 = 0;
		int counter3 = 0;

		for (int i = 0; i < runtime.size(); i++) {
			if (runtime.get(i) != null) {
				if (runtime.get(i) <= 60) {
					counter1++;
				} else if (runtime.get(i) > 60 && runtime.get(i) < 90) {
					counter2++;
				} else if (runtime.get(i) >= 90) {
					counter3++;
				}
			}
		}
		numberOfFilmsRT[0] = counter1;
		numberOfFilmsRT[1] = counter2;
		numberOfFilmsRT[2] = counter3;
	}
}
