import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ContractService } from '../../../core/services/contract.service';
import { Router, RouterLink } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-contract-load',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './contract-load.component.html',
  styleUrls: ['./contract-load.component.scss']
})
export class ContractLoadComponent {
  contractForm: FormGroup;
  isSubmitting = false;

  constructor(
    private fb: FormBuilder,
    private contractService: ContractService,
    private toastr: ToastrService,
    private router: Router
  ) {
    this.contractForm = this.fb.group({
      hotelName: ['', Validators.required],
      validFrom: ['', Validators.required],
      validUntil: ['', Validators.required],
      roomTypes: this.fb.array([]) // Initialize FormArray
    });

    // Add one default room type
    this.addRoomType();
  }

  get roomTypes(): FormArray {
    return this.contractForm.get('roomTypes') as FormArray;
  }

  createRoomType(): FormGroup {
    return this.fb.group({
      typeName: ['', Validators.required],
      noOfRoomsAvailable: [0, [Validators.required, Validators.min(1)]],
      pricePerPerson: [0, [Validators.required, Validators.min(0.01)]],
      maxAdults: [1, [Validators.required, Validators.min(1)]]
    });
  }

  addRoomType() {
    this.roomTypes.push(this.createRoomType());
  }

  removeRoomType(index: number) {
    this.roomTypes.removeAt(index);
    this.roomTypes.updateValueAndValidity();
  }

  onSubmit() {
    // console.log(this.contractForm.value);
    if (this.contractForm.invalid) {
      
      this.contractForm.markAllAsTouched();
      this.toastr.error('Invalid Data.', 'Error');
      return;
    }

    this.isSubmitting = true;

    const formValue = this.contractForm.value;
    const requestBody = {
      hotel: { hotelName: formValue.hotelName },
      validFrom: formValue.validFrom,
      validUntil: formValue.validUntil,
      roomTypes: formValue.roomTypes
    };
    
    this.contractService.createContract(requestBody).subscribe({
      next: (response) => {
        this.isSubmitting = false;
        this.toastr.success('Contract created successfully!', 'Success');
        this.router.navigate(['/contracts/view']); // Navigate to the contracts view page
      },
      error: (err) => {
        this.isSubmitting = false;
        this.toastr.error('Failed to create contract. Please try again.', 'Error');
      }
    });
  }
}
