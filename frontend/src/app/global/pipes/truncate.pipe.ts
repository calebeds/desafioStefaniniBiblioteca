import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'truncate'
})
export class TruncatePipe implements PipeTransform {
    transform(value: string, ...args: any[]): string {
        const limit = args.length > 0 ?  parseInt(args[0], 10) : 20; // Ternary to set a default value
        const trail = args.length > 1 ? args[1] : '...';// Ternary for the same thing

        // If the amount of character is greater than the limit, so substring it with the limit
        return value.length > limit ? value.substring(0, limit) + trail : value;
    }
}