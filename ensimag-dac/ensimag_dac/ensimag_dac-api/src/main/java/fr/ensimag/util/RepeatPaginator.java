/**
 * @copyright at69
 * @see https://github.com/at69/CoffeeRoom/blob/master/src/java/com/coffeeroom/util/RepeatPaginator.java
 */

package fr.ensimag.util;

import java.util.List;

public class RepeatPaginator {
	private static final int DEFAULT_RECORDS_NUMBER = 10;
	private static final int DEFAULT_PAGE_INDEX     = 1;
	private final int     recordsTotal;
	private final List<?> origModel;
	private       int     records;
	private       int     pageIndex;
	private       int     pages;
	private       List<?> model;

	public RepeatPaginator(final List<?> model) {
		this(model, RepeatPaginator.DEFAULT_RECORDS_NUMBER);
	}

	public RepeatPaginator(final List<?> model, int records) {
		this.origModel = model;
		this.records = records;
		this.pageIndex = RepeatPaginator.DEFAULT_PAGE_INDEX;
		this.recordsTotal = model.size();

		if (this.records > 0) {
			this.pages = this.records <= 0 ? 1 : this.recordsTotal / this.records;

			if (this.recordsTotal % this.records > 0) {
				this.pages++;
			}

			if (this.pages == 0) {
				this.pages = 1;
			}
		} else {
			this.records = 1;
			this.pages = 1;
		}

		this.updateModel();
	}

	private void updateModel() {
		final int fromIndex = this.getFirst();
		int toIndex = this.getFirst() + this.records;

		if (toIndex > this.recordsTotal) {
			toIndex = this.recordsTotal;
		}

		this.model = this.origModel.subList(fromIndex, toIndex);
	}

	public void next() {
		if (this.pageIndex < this.pages) {
			this.pageIndex++;
		}

		this.updateModel();
	}

	public void prev() {
		if (this.pageIndex > 1) {
			this.pageIndex--;
		}

		this.updateModel();
	}

	public int getRecords() {
		return this.records;
	}

	public int getRecordsTotal() {
		return this.recordsTotal;
	}

	public int getPageIndex() {
		return this.pageIndex;
	}

	public void setPageIndex(final int pageIndex) {
		if (pageIndex <= this.pages && pageIndex >= 1) {
			this.pageIndex = pageIndex;

			this.updateModel();
		}
	}

	public int getPages() {
		return this.pages;
	}

	public int getFirst() {
		return this.pageIndex * this.records - this.records;
	}

	public List<?> getModel() {
		return this.model;
	}
}
