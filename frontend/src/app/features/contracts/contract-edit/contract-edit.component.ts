import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ContractService } from '../../../core/services/contract.service';

@Component({
  selector: 'app-contract-edit',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './contract-edit.component.html',
  styleUrls: ['./contract-edit.component.scss']
})
export class ContractEditComponent implements OnInit {
  contractForm: FormGroup;
  loading = false;
  error: string | null = null;
  successMessage: string | null = null;
  contractId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private contractService: ContractService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.contractForm = this.fb.group({
      hotelName: ['', Validators.required],
      validFrom: ['', Validators.required],
      validUntil: ['', Validators.required],
      roomTypes: this.fb.array([])
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      if (idParam) {
        const id = Number(idParam);
        if (!isNaN(id)) {
          this.contractId = id;
          this.loadContract(id);
        }
      } else {
        this.error = 'No contract ID provided.';
      }
    });
  }

  get roomTypes(): FormArray {
    return this.contractForm.get('roomTypes') as FormArray;
  }

  addRoomType() {
    this.roomTypes.push(this.fb.group({
      typeName: ['', Validators.required],
      noOfRoomsAvailable: [0, [Validators.required, Validators.min(1)]],
      pricePerPerson: [0, [Validators.required, Validators.min(0.01)]],
      maxAdults: [1, [Validators.required, Validators.min(1)]]
    }));
  }

  removeRoomType(index: number) {
    this.roomTypes.removeAt(index);
  }

  loadContract(id: number) {
    this.loading = true;
    this.contractService.getContract(id).subscribe({
      next: (data) => {
        this.loading = false;
        // Populate the form with existing data
        this.contractForm.patchValue({
          hotelName: data.hotel.hotelName,
          validFrom: data.validFrom,   // if string (YYYY-MM-DD), it should bind correctly to input[type=date]
          validUntil: data.validUntil
        });

        // Clear current roomTypes and repopulate
        this.roomTypes.clear();
        data.roomTypes.forEach(rt => {
          this.roomTypes.push(this.fb.group({
            typeName: [rt.typeName, Validators.required],
            noOfRoomsAvailable: [rt.noOfRoomsAvailable, [Validators.required, Validators.min(1)]],
            pricePerPerson: [rt.pricePerPerson, [Validators.required, Validators.min(0.01)]],
            maxAdults: [rt.maxAdults, [Validators.required, Validators.min(1)]]
          }));
        });
      },
      error: (err) => {
        this.loading = false;
        console.error('Error fetching contract:', err);
        this.error = 'Failed to load contract.';
      }
    });
  }

  onSubmit() {
    if (!this.contractId) {
      this.error = 'No contract ID set, cannot update.';
      return;
    }

    if (this.contractForm.invalid) {
      this.contractForm.markAllAsTouched();
      return;
    }

    const formValue = this.contractForm.value;
    const requestBody = {
      hotel: { hotelName: formValue.hotelName },
      validFrom: formValue.validFrom,
      validUntil: formValue.validUntil,
      roomTypes: formValue.roomTypes
    };

    this.loading = true;
    this.error = null;
    this.successMessage = null;

    this.contractService.updateContract(this.contractId, requestBody).subscribe({
      next: (res) => {
        this.loading = false;
        this.successMessage = 'Contract updated successfully.';
        // Optionally navigate back to a view page after a delay
        // setTimeout(() => this.router.navigate(['/contracts/view', this.contractId]), 2000);
      },
      error: (err) => {
        this.loading = false;
        console.error('Error updating contract:', err);
        this.error = 'Failed to update contract.';
      }
    });
  }
}
