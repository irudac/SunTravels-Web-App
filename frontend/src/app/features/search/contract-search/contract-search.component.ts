import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ContractService } from '../../../core/services/contract.service';
import { SearchStateService } from '../../../core/services/search-state.service';

@Component({
  selector: 'app-contract-search',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './contract-search.component.html',
  styleUrls: ['./contract-search.component.scss']
})
export class ContractSearchComponent {
  searchForm: FormGroup;
  loading = false;
  error: string | null = null;

  constructor(
    private fb: FormBuilder,
    private contractService: ContractService,
    private searchStateService: SearchStateService,
    private router: Router
  ) {
    this.searchForm = this.fb.group({
      checkInDate: ['', Validators.required],
      numOfNights: [1, [Validators.required, Validators.min(1)]],
      roomsRequiredList: this.fb.array([])
    });
    this.addRoomRequirement(); // start with one room requirement row
  }

  get roomsRequiredList(): FormArray {
    return this.searchForm.get('roomsRequiredList') as FormArray;
  }

  addRoomRequirement() {
    this.roomsRequiredList.push(this.fb.group({
      numOfRooms: [1, [Validators.required, Validators.min(1)]],
      adultsPerRoom: [1, [Validators.required, Validators.min(1)]]
    }));
  }

  removeRoomRequirement(index: number) {
    this.roomsRequiredList.removeAt(index);
  }

  onSearch() {
    if (this.searchForm.invalid) {
      this.searchForm.markAllAsTouched();
      return;
    }

    const formValue = this.searchForm.value;
    const requestBody = {
      checkInDate: formValue.checkInDate,
      numOfNights: formValue.numOfNights,
      roomsRequiredList: formValue.roomsRequiredList
    };

    this.loading = true;
    this.error = null;
    this.contractService.searchContracts(requestBody).subscribe({
      next: (results) => {
        this.loading = false;
        this.searchStateService.setResults(results);
        this.router.navigate(['/search/results']);
      },
      error: (err) => {
        console.error('Search error', err);
        this.error = 'Failed to fetch search results.';
        this.loading = false;
      }
    });
  }
}
