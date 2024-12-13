import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ContractService } from '../../../core/services/contract.service';
import { Contract } from '../../../models/contract.model';

@Component({
  selector: 'app-contract-view',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './contract-view.component.html',
  styleUrls: ['./contract-view.component.scss']
})
export class ContractViewComponent implements OnInit {
  contract: Contract | null = null;
  contracts: Contract[] = [];
  loading = false;
  error: string | null = null;

  private currentId: number | null = null;

  constructor(
    private contractService: ContractService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const idParam = params.get('id');
      if (idParam) {
        const id = Number(idParam);
        if (!isNaN(id)) {
          this.loadSingleContract(id);
          this.currentId = id;
        }
      } else {
        this.loadAllContracts();
      }
    });
  }

  loadSingleContract(id: number) {
    this.loading = true;
    this.error = null;
    this.contractService.getContract(id).subscribe({
      next: (data) => {
        this.contract = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error fetching contract', err);
        this.error = 'Failed to load contract.';
        this.loading = false;
      }
    });
  }

  loadAllContracts() {
    this.loading = true;
    this.error = null;
    this.contractService.getAllContracts().subscribe({
      next: (data) => {
        this.contracts = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error fetching contracts', err);
        this.error = 'Failed to load contracts.';
        this.loading = false;
      }
    });
  }

  deleteContract() {
    if (this.currentId) {
      this.loading = true;
      this.contractService.deleteContract(this.currentId).subscribe({
        next: (message) => {
          console.log('Delete successful:', message);
          this.loading = false;
          // After deletion, navigate back to all contracts
          this.router.navigate(['/contracts/view']);
        },
        error: (err) => {
          console.error('Error deleting contract:', err);
          this.error = 'Failed to delete contract.';
          this.loading = false;
        }
      });
    }
  }

  getContractStatus(validFrom: string, validUntil: string): string {
    const now = new Date();
    const fromDate = new Date(validFrom);
    const untilDate = new Date(validUntil);
  
    if (now >= fromDate && now <= untilDate) {
      return 'Valid'; // Current date is within the range
    } else if (now < fromDate) {
      return 'Upcoming'; // Future date range
    } else {
      return 'Expired'; // Past date range
    }
  }  
  
}
