package com.daynilgroup.DaynilGroupAssignment.model;

import java.util.List;

import lombok.Data;

@Data
public class EntryRoot {
	private String count;
	private List<Entries> entries;
}
