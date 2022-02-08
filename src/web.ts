import { WebPlugin } from '@capacitor/core';

import type { FlashPlugin } from './definitions';

export class FlashWeb extends WebPlugin implements FlashPlugin {
  async isAvailable(): Promise<{ value: boolean; }> {
    throw new Error('Method not implemented.');
  }
  async switchOn(_options: { intensity?: number | undefined; }): Promise<void> {
    throw new Error('Method not implemented.');
  }
  async switchOff(): Promise<void> {
    throw new Error('Method not implemented.');
  }
  async isSwitchedOn(): Promise<{ value: boolean; }> {
    throw new Error('Method not implemented.');
  }
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
