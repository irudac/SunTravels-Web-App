import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchStateService } from '../../../core/services/search-state.service';

@Component({
  selector: 'app-contract-results',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './contract-results.component.html',
  styleUrls: ['./contract-results.component.scss']
})
export class ContractResultsComponent implements OnInit {
  results: any[] | null = null;
  

  constructor(private searchStateService: SearchStateService) {}

  ngOnInit(): void {
    this.results = this.searchStateService.getResults();
  }
}
