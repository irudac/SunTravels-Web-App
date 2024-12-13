export interface Contract {
  contractId: number;
  hotel: {
    hotelId: number;
    hotelName: string;
  };
  validFrom: string;
  validUntil: string;
  roomTypes: {
    typeId: number;
    typeName: string;
    pricePerPerson: number;
    noOfRoomsAvailable: number;
    maxAdults: number;
  }[];
}

export interface SearchRequest {
  checkInDate: string;  // 'YYYY-MM-DD'
  numOfNights: number;
  roomsRequiredList: RoomsRequest[];
}

export interface SearchResponse {
  hotel: Hotel;
  roomList: RoomsResponse[];
  totalPrice: number;
}

interface Hotel {
  hotelId: number;
  hotelName: string;
  contracts: any; // or null
}

interface RoomsResponse {
  typeId: number;
  pricePerPersonMarkedUp: number;
  maxAdults: number;
  numberOfRoomsRequested: number;
  typeName: string;
  totalPriceForAllSameTypeRooms: number;
}

interface RoomsRequest {
  numOfRooms: number;
  adultsPerRoom: number;
}