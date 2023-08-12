package com.github.topi314.lavasearch;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchManager {

	private final List<SearchSourceManager> sourceManagers;

	public SearchManager() {
		this.sourceManagers = new ArrayList<>();
	}

	public void registerSourceManager(SearchSourceManager sourceManager) {
		sourceManagers.add(sourceManager);
	}

	@Nullable
	public <T extends SearchSourceManager> T source(Class<T> klass) {
		for (var sourceManager : sourceManagers) {
			if (klass.isAssignableFrom(sourceManager.getClass())) {
				return klass.cast(sourceManager);
			}
		}

		return null;
	}

	public List<SearchSourceManager> getSourceManagers() {
		return this.sourceManagers;
	}

	public void shutdown() {
		for (var sourceManager : this.sourceManagers) {
			sourceManager.shutdown();
		}
	}

	@Nullable
	public AudioSearchResult loadSearch(String query, Set<AudioSearchResult.Type> types) {
		for (var sourceManager : this.sourceManagers) {
			var searchResults = sourceManager.loadSearch(query, types);
			if (searchResults != null) {
				return searchResults;
			}
		}
		return null;
	}

}
