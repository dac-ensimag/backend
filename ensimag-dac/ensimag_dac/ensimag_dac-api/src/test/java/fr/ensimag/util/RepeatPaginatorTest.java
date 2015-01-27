package fr.ensimag.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RepeatPaginatorTest {
	private List<Integer> model = new ArrayList<>();
	private RepeatPaginator paginator;

	@Before
	public void before() {
		for (int i = 0 ; i < 35 ; i++) {
			this.model.add(new Integer(i));
		}

		this.paginator = new RepeatPaginator(this.model);
	}

	@Test
	public void testSimpleConstructor() {
		Assert.assertEquals(4, this.paginator.getPages());
		Assert.assertEquals(0, this.paginator.getFirst());
		Assert.assertEquals(10, this.paginator.getRecords());
		Assert.assertEquals(35, this.paginator.getRecordsTotal());
		Assert.assertEquals(1, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(0, 10), this.paginator.getModel());
	}

	@Test
	public void testFullConstructor() {
		this.paginator = new RepeatPaginator(this.model, 1);

		Assert.assertEquals(35, this.paginator.getPages());
		Assert.assertEquals(0, this.paginator.getFirst());
		Assert.assertEquals(1, this.paginator.getRecords());
		Assert.assertEquals(35, this.paginator.getRecordsTotal());
		Assert.assertEquals(1, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(0, 1), this.paginator.getModel());

		this.paginator = new RepeatPaginator(this.model, 50);

		Assert.assertEquals(1, this.paginator.getPages());
		Assert.assertEquals(0, this.paginator.getFirst());
		Assert.assertEquals(50, this.paginator.getRecords());
		Assert.assertEquals(35, this.paginator.getRecordsTotal());
		Assert.assertEquals(1, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(0, 35), this.paginator.getModel());
	}

	/**
	 * Method: next()
	 */
	@Test
	public void testNext() {
		Assert.assertEquals(0, this.paginator.getFirst());
		Assert.assertEquals(1, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(0, 10), this.paginator.getModel());

		this.paginator.next();

		Assert.assertEquals(10, this.paginator.getFirst());
		Assert.assertEquals(2, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(10, 20), this.paginator.getModel());

		this.paginator.next();

		Assert.assertEquals(20, this.paginator.getFirst());
		Assert.assertEquals(3, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(20, 30), this.paginator.getModel());

		this.paginator.next();

		Assert.assertEquals(30, this.paginator.getFirst());
		Assert.assertEquals(4, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(30, 35), this.paginator.getModel());

		this.paginator.next();

		Assert.assertEquals(30, this.paginator.getFirst());
		Assert.assertEquals(4, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(30, 35), this.paginator.getModel());
	}

	/**
	 * Method: prev()
	 */
	@Test
	public void testPrev() {
		Assert.assertEquals(0, this.paginator.getFirst());
		Assert.assertEquals(1, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(0, 10), this.paginator.getModel());

		this.paginator.prev();

		Assert.assertEquals(0, this.paginator.getFirst());
		Assert.assertEquals(1, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(0, 10), this.paginator.getModel());

		this.paginator.next();
		this.paginator.prev();

		Assert.assertEquals(0, this.paginator.getFirst());
		Assert.assertEquals(1, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(0, 10), this.paginator.getModel());
	}

	/**
	 * Method: setPageIndex(final int pageIndex)
	 */
	@Test
	public void testSetPageIndex() {
		Assert.assertEquals(0, this.paginator.getFirst());
		Assert.assertEquals(1, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(0, 10), this.paginator.getModel());

		this.paginator.setPageIndex(0);

		Assert.assertEquals(0, this.paginator.getFirst());
		Assert.assertEquals(1, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(0, 10), this.paginator.getModel());

		this.paginator.setPageIndex(-1);

		Assert.assertEquals(0, this.paginator.getFirst());
		Assert.assertEquals(1, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(0, 10), this.paginator.getModel());

		this.paginator.setPageIndex(100);

		Assert.assertEquals(0, this.paginator.getFirst());
		Assert.assertEquals(1, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(0, 10), this.paginator.getModel());

		this.paginator.setPageIndex(2);

		Assert.assertEquals(10, this.paginator.getFirst());
		Assert.assertEquals(2, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(10, 20), this.paginator.getModel());

		this.paginator.setPageIndex(4);

		Assert.assertEquals(30, this.paginator.getFirst());
		Assert.assertEquals(4, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(30, 35), this.paginator.getModel());

		this.paginator.setPageIndex(100);

		Assert.assertEquals(30, this.paginator.getFirst());
		Assert.assertEquals(4, this.paginator.getPageIndex());
		Assert.assertEquals(this.model.subList(30, 35), this.paginator.getModel());
	}
} 
