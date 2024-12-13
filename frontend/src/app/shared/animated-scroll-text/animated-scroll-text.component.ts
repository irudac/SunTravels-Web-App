import { Component, AfterViewInit, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { gsap } from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';

gsap.registerPlugin(ScrollTrigger);

@Component({
  selector: 'app-animated-scroll-text',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './animated-scroll-text.component.html',
  styleUrls: ['./animated-scroll-text.component.scss']
})
export class AnimatedScrollTextComponent implements AfterViewInit {
  @ViewChild('containerRef', { static: false }) containerRef!: ElementRef;

  ngAfterViewInit(): void {
    const container = document.querySelector('.animated-scroll-text-container');
    const lineOne = document.querySelector('.line-one');
    const lineTwo = document.querySelector('.line-two');

    // GSAP Timeline tied to ScrollTrigger
    const tl = gsap.timeline({
      scrollTrigger: {
        trigger: container, // Trigger the animation based on the container
        start: "top bottom", // Start animation when the top of container hits the bottom of viewport
        end: "top 25%", // End animation when the container's top is 25% from top of viewport
        scrub: true, // Animations are linked to scroll position
      }
    });

    // Animate lineOne from right to center
    tl.fromTo(
      lineOne,
      { x: '100%', opacity: 0 }, // Initial position (off-screen to the right)
      { x: '0%', opacity: 1, duration: 3, delay: 0.5 } // Final position (centered) with 0.5s delay
    );
    
    // Animate lineTwo from left to center with additional delay
    tl.fromTo(
      lineTwo,
      { x: '-100%', opacity: 0 }, // Initial position (off-screen to the left)
      { x: '0%', opacity: 1, duration: 3, delay: 0.5 }, '<' // Starts simultaneously with lineOne but respects delay
    );
  }
}
