// search-state.service.ts
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SearchStateService {
  private results: any[] | null = null;

  setResults(data: any[]) {
    this.results = data;
  }

  getResults(): any[] | null {
    return this.results;
  }

  clearResults() {
    this.results = null;
  }
}
