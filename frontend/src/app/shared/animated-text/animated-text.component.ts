import { Component, AfterViewInit, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { gsap } from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';

gsap.registerPlugin(ScrollTrigger);

@Component({
  selector: 'app-animated-text',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './animated-text.component.html',
  styleUrls: ['./animated-text.component.scss'],
})
export class AnimatedTextComponent implements AfterViewInit {
  @ViewChild('animatedContainer', { static: false }) animatedContainer!: ElementRef;

  ngAfterViewInit(): void {
    const paragraphs = document.querySelectorAll('.animated-text p');

    gsap.to(paragraphs, {
      opacity: 1,
      y: 0,
      duration: 1,
      stagger: 0.3, // Delay between animations for each paragraph
      scrollTrigger: {
        trigger: '.animated-container',
        start: 'top 80%', // Start animation when container is 80% from top
        end: 'bottom top', // End animation when container exits viewport
        toggleActions: 'play none none reset',
      },
    });
  }
}
