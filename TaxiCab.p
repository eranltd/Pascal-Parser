PRoGRam TaxiCab;

type
    node = record
        number : integer;
        occurences : integer;
        left,right : ^node;
    end;
    nodep = ^node;

var
    loopCounter : integer;
    dummy : real;

(* Returns the number of occurences of the argument number in the tree root *)
function occurencesOf (var root: nodep; number : integer) : integer;
begin
    loopCounter := loopcounter + 1; 
    if root = nil then begin
        new (root);
        root^.number := number;
        root^.occurences := 1;
        occurencesOf := root^.occurences;
    end else if number = root^.number then begin
        root^.occurences := root^.occurences + 1;
        occurencesOf := root^.occurences;
    end else if number < root^.number then 
        occurencesOf := occurencesOf (root^.left, number)
    else
        occurencesOf := occurencesOf (root^.right, number)
end;

(* Returns the Taxi Cab number of the given index
   where taxicabNumber(1) is 2 *)
function taxicabNumber (index : integer) : integer;
var
    root : ^node;
    sum,i,j : integer;
    found : boolean;

begin
    root := nil;
    sum := 1;
    found := false;
    while not found do begin
        i := 1;
        j := sum - 1;
        while not found and (i <= j) do begin
            loopCounter := loopcounter + 1;
            taxicabNumber := i * i * i + j * j * j;
            found := occurencesOf (root, taxicabNumber) = index;
            i := i + 1;
            j := j - 1;
        end;
        sum := sum + 1;
    end;
end;


begin
    loopCounter := 0;
    dummy := 5.5;
    WriteLn('The first Taxi Cab number after 2 is:', taxicabNumber(2), ',Computed in ', loopCounter, ' iterations');
end .
